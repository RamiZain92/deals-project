package com.cybersolution.fazeal.logistics.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.response.VehicleDetailsResponse;
import com.cybersolution.fazeal.logistics.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class DriverQuery implements GraphQLQueryResolver {

    @Autowired
    private DriverService driverService;

    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public List<VehicleDetailsResponse> getUserDrivingMethods(){
        return driverService.getUserDrivingMethods();
    }
}
