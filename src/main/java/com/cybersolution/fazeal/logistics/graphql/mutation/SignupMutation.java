package com.cybersolution.fazeal.logistics.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cybersolution.fazeal.common.album.dto.DefaultImage;
import com.cybersolution.fazeal.common.album.feign.AlbumApiClient;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.dto.AddressDTO;
import com.cybersolution.fazeal.logistics.dto.SignUpUserDTO;
import com.cybersolution.fazeal.logistics.dto.VehicleDetailsDTO;
import com.cybersolution.fazeal.logistics.service.SignUpService;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.response.SignUpResponse;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class SignupMutation implements GraphQLMutationResolver {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private Messages messages;

    @Autowired
    private AlbumApiClient albumApiClient;

    public SignUpResponse createUserInfo(@Valid SignUpUserDTO signUpUserDTO, DataFetchingEnvironment dataFetchingEnvironment){
        if(Objects.isNull(Utility.getImageFileByIndex(dataFetchingEnvironment, 0))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.DRIVER_ID_IMAGE_REQUIRED));
        }
        if(Objects.isNull(Utility.getImageFileByIndex(dataFetchingEnvironment, 1))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.DRIVER_LICENSE_IMAGE_REQUIRED));
        }
        if(Objects.isNull(Utility.getImageFileByIndex(dataFetchingEnvironment, 2))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.DRIVER_PERSONAL_IMAGE_REQUIRED));
        }
        signUpUserDTO.setUserIDImagePath(albumApiClient.uploadImageAndGetA_PathToLink(DefaultImage.builder().imageFile(Utility.getImageFileByIndex(dataFetchingEnvironment, 0)).build()));
        signUpUserDTO.setUserLicenseNumberImage(albumApiClient.uploadImageAndGetA_PathToLink(DefaultImage.builder().imageFile(Utility.getImageFileByIndex(dataFetchingEnvironment, 1)).build()));
        signUpUserDTO.setUserPersonalImage(albumApiClient.uploadImageAndGetA_PathToLink(DefaultImage.builder().imageFile(Utility.getImageFileByIndex(dataFetchingEnvironment, 2)).build()));
        return signUpService.createUserInfo(signUpUserDTO);
    }

    public SignUpResponse createVehicleDetails(VehicleDetailsDTO vehicleDetailsDTO){
        return signUpService.createVehicleDetails(vehicleDetailsDTO);
    }

    public SignUpResponse uploadVehicleImages(Long userId, Long vehicleId, DataFetchingEnvironment dataFetchingEnvironment){
        if(Objects.isNull(Utility.getImagesFiles(dataFetchingEnvironment))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_IMAGES_EMPTY));
        }
        List<String> images = new ArrayList<>();
        Utility.getImagesFiles(dataFetchingEnvironment).forEach(file -> {
            images.add(albumApiClient.uploadImageAndGetA_PathToLink(DefaultImage.builder().imageFile(file).build()));
        });
        return signUpService.uploadVehicleImages(userId, vehicleId, images);
    }

    public SignUpResponse createAddressInfo(AddressDTO addressDTO){
        return signUpService.createAddressInfo(addressDTO);
    }

    public SignUpResponse agreements(Long userId){
        return signUpService.agreements(userId);
    }


}
