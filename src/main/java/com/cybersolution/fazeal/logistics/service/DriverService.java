package com.cybersolution.fazeal.logistics.service;


import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.logistics.dto.AddressDTO;
import com.cybersolution.fazeal.logistics.dto.SignUpUserDTO;
import com.cybersolution.fazeal.logistics.dto.VehicleDetailsDTO;
import com.cybersolution.fazeal.logistics.response.SignUpResponse;
import com.cybersolution.fazeal.logistics.response.VehicleDetailsResponse;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface DriverService {

    public MessageResponse setDriverAvailability(Boolean available);

    public List<VehicleDetailsResponse> getUserDrivingMethods();

    public MessageResponse selectDrivingMethod(Long vehicleId);

    MessageResponse uploadVehicleImages(Long vehicleId,  List<CommonsMultipartFile> images);
}
