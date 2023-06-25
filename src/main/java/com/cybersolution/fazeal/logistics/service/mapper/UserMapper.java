package com.cybersolution.fazeal.logistics.service.mapper;

import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.response.AddressResponse;
import com.cybersolution.fazeal.logistics.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse userEntityToResponse(UserEntity userEntity){
        return UserResponse.builder()
                .userId(userEntity.getId())
                .email(userEntity.getEmail())
                .userName(userEntity.getUserName())
                .userIdImageUrl(userEntity.getUserIdImageUrl())
                .contactNumber(userEntity.getContactNumber())
                .dateOfBirth(userEntity.getDateOfBirth().toString())
                .enabled(userEntity.getEnabled())
                .availability(userEntity.getAvailability())
                .genderType(userEntity.getGenderType().toString())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .userLicenceNumberImageUrl(userEntity.getUserLicenceNumberImageUrl())
                .userPersonalImageUrl(userEntity.getUserPersonalImageUrl())
                .signUpProcess(userEntity.getSignUpProcess().toString())
                .addressResponse(AddressResponse.builder()
                        .id(userEntity.getAddressEntity().getId())
                        .city(userEntity.getAddressEntity().getCity())
                        .state(userEntity.getAddressEntity().getState())
                        .country(userEntity.getAddressEntity().getCountry().getName())
                        .addressLineOne(userEntity.getAddressEntity().getAddressLineOne())
                        .addressLineTwo(userEntity.getAddressEntity().getAddressLineTwo())
                        .description(userEntity.getAddressEntity().getDescription())
                        .zipCode(userEntity.getAddressEntity().getZipCode())
                        .locationName(userEntity.getAddressEntity().getLocationName())
                        .streetNumber(userEntity.getAddressEntity().getStreetNumber())
                        .build())
                .build();
    }
}
