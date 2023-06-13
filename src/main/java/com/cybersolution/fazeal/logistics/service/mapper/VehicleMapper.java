package com.cybersolution.fazeal.logistics.service.mapper;

import com.cybersolution.fazeal.logistics.dto.VehicleDetailsDTO;
import com.cybersolution.fazeal.logistics.entity.VehicleEntity;
import com.cybersolution.fazeal.logistics.response.VehicleDetailsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleMapper {

    public VehicleDetailsResponse mapVehicleDtoToResponse(VehicleEntity vehicleEntity){
        VehicleDetailsResponse vehicleDetailsResponse = new VehicleDetailsResponse();
        vehicleDetailsResponse.setId(vehicleEntity.getId());
        vehicleDetailsResponse.setCapacity(vehicleEntity.getCapacity());
        vehicleDetailsResponse.setBrand(vehicleEntity.getBrand());
        vehicleDetailsResponse.setColor(vehicleEntity.getColor());
        vehicleDetailsResponse.setType(vehicleEntity.getType());
        vehicleDetailsResponse.setStatus(vehicleEntity.getStatus());
        vehicleDetailsResponse.setFuelType(vehicleEntity.getFuelType());
        vehicleDetailsResponse.setUserId(vehicleEntity.getUserEntity().getId());
        vehicleDetailsResponse.setDrivingMethod(vehicleEntity.getDrivingMethod());
        vehicleDetailsResponse.setLicensePlateNumber(vehicleEntity.getLicensePlate());
        vehicleDetailsResponse.setMileAge(vehicleEntity.getMileage());
        vehicleDetailsResponse.setYear(vehicleEntity.getYear());
        vehicleDetailsResponse.setModelName(vehicleEntity.getModelName());
        vehicleDetailsResponse.setEnabled(vehicleEntity.getEnabled());
        vehicleDetailsResponse.setVehicleImages(vehicleEntity.getVehicleImagesEntities()
                .stream().map(image -> image.getImagePath()).collect(Collectors.toList()));
        return vehicleDetailsResponse;
    }

    public List<VehicleDetailsResponse> mapVehicleListDtoToResponse(List<VehicleEntity> vehicleEntities){
        return vehicleEntities.stream().map(vehicle -> mapVehicleDtoToResponse(vehicle))
                .collect(Collectors.toList());
    }
}
