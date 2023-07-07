package com.cybersolution.fazeal.logistics.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.service.DriverService;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Service
@Validated
public class DriverMutation implements GraphQLMutationResolver {

    @Autowired
    private DriverService driverService;
    @Autowired
    private Messages messages;

    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse setDriverAvailability(Boolean available){
        return driverService.setDriverAvailability(available);
    }

    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse selectDrivingMethod(Long vehicleId){
        return driverService.selectDrivingMethod(vehicleId);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse addVehicleImages(Long vehicleId, DataFetchingEnvironment dataFetchingEnvironment){
        if(Objects.isNull(Utility.getImagesFiles(dataFetchingEnvironment))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_IMAGES_EMPTY));
        }
        return driverService.uploadVehicleImages(vehicleId, Utility.getImagesFiles(dataFetchingEnvironment));
    }
}
