package com.cybersolution.fazeal.logistics.service;


import com.cybersolution.fazeal.logistics.dto.AddressDTO;
import com.cybersolution.fazeal.logistics.dto.SignUpUserDTO;
import com.cybersolution.fazeal.logistics.dto.VehicleDetailsDTO;
import com.cybersolution.fazeal.logistics.response.SignUpResponse;

import java.util.List;

public interface SignUpService {

    public SignUpResponse createUserInfo(SignUpUserDTO signUpUserDTO);

    public SignUpResponse createVehicleDetails(VehicleDetailsDTO vehicleDetailsDTO);

    public SignUpResponse uploadVehicleImages(Long userId, Long vehicleId, List<String> images);

    public SignUpResponse createAddressInfo(AddressDTO addressDTO);

    public SignUpResponse agreements(Long userId);


}
