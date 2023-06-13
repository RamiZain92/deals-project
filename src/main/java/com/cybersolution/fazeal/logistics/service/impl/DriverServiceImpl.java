package com.cybersolution.fazeal.logistics.service.impl;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.entity.VehicleEntity;
import com.cybersolution.fazeal.logistics.repository.UserRepository;
import com.cybersolution.fazeal.logistics.repository.VehicleRepository;
import com.cybersolution.fazeal.logistics.response.VehicleDetailsResponse;
import com.cybersolution.fazeal.logistics.service.UserService;
import com.cybersolution.fazeal.logistics.service.DriverService;
import com.cybersolution.fazeal.logistics.service.mapper.VehicleMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private UserService userService;

    @Autowired
    private Messages messages;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;


    @Override
    public MessageResponse setDriverAvailability(Boolean available) {
        if(Objects.isNull(available)){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.AVAILABLE_REQUIRED));
        }
        UserEntity userEntity =  userService.getLoggedUser();
        userEntity.setAvailability(available);
        userRepository.save(userEntity);
        return MessageResponse.builder().message(messages.get(AppConstants.DRIVER_AVAILABILITY_UPDATED) + available ).build();
    }

    @Override
    public List<VehicleDetailsResponse> getUserDrivingMethods() {
        UserEntity userEntity =  userService.getLoggedUser();
        List<VehicleEntity> vehicleEntities = vehicleRepository.findByUserEntity(userEntity);
        return vehicleMapper.mapVehicleListDtoToResponse(vehicleEntities);
    }

    @Override
    public MessageResponse selectDrivingMethod(Long vehicleId) {
        if(Objects.isNull(vehicleId)){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.VEHICLE_ID_REQUIRED));
        }
        UserEntity userEntity =  userService.getLoggedUser();
        VehicleEntity vehicleEntity = vehicleRepository.findByIdAndUserEntity(vehicleId, userEntity)
                .orElseThrow(() -> new GenericException(HttpStatus.CONFLICT, AppConstants.VEHICLE_NOT_FOUND,
                        messages.get(AppConstants.VEHICLE_NOT_FOUND)));
        userEntity.getVehicleEntities().forEach(vehicle -> vehicle.setEnabled(false));
        vehicleEntity.setEnabled(true);
        vehicleRepository.save(vehicleEntity);
        return MessageResponse.builder().message(messages.get(AppConstants.DRIVING_METHOD_UPDATED)).build();
    }
}
