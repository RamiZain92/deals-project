package com.cybersolution.fazeal.logistics.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cybersolution.fazeal.common.album.dto.DefaultImage;
import com.cybersolution.fazeal.common.album.feign.AlbumApiClient;
import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.common.logistics.dto.UpdateContactNumberDTO;
import com.cybersolution.fazeal.common.logistics.dto.UpdatePasswordDTO;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.service.UserService;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private Messages messages;
    @Autowired
    private AlbumApiClient albumApiClient;

    @Autowired
    private HttpServletResponse response;
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateContactNumber(UpdateContactNumberDTO updateContactNumberDTO){
        return userService.updateContactNumber(updateContactNumberDTO);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updatePassword(@Valid UpdatePasswordDTO updatePasswordDTO){
        return userService.updatePassword(updatePasswordDTO);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateEmail(String email){
        return userService.updateEmail(email);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateProfileImage(DataFetchingEnvironment dataFetchingEnvironment){
        String imageUrl = null;
        if(Objects.isNull(Utility.getImageFileByIndex(dataFetchingEnvironment, 0))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.IMAGE_CANT_BE_BLANK));
        }
        imageUrl=albumApiClient.uploadImageAndGetA_PathToLink(DefaultImage.builder().imageFile(Utility.getImageFileByIndex(dataFetchingEnvironment, 0)).build());
        return userService.updateProfileImage(imageUrl);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateLicenceNumberImage(DataFetchingEnvironment dataFetchingEnvironment){
        String imageUrl=null;
        if(Objects.isNull(Utility.getImageFileByIndex(dataFetchingEnvironment, 0))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.IMAGE_CANT_BE_BLANK));
        }
        imageUrl=albumApiClient.uploadImageAndGetA_PathToLink(DefaultImage.builder().imageFile(Utility.getImageFileByIndex(dataFetchingEnvironment, 0)).build());
        return userService.updateLicenceNumberImage(imageUrl);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateIdImage(DataFetchingEnvironment dataFetchingEnvironment){
        String imageUrl=null;
        if(Objects.isNull(Utility.getImageFileByIndex(dataFetchingEnvironment, 0))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.IMAGE_CANT_BE_BLANK));
        }
        imageUrl=albumApiClient.uploadImageAndGetA_PathToLink(DefaultImage.builder().imageFile(Utility.getImageFileByIndex(dataFetchingEnvironment, 0)).build());
        return userService.updateIdImage(imageUrl);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse addVehicleImages(Long vehicleId, DataFetchingEnvironment dataFetchingEnvironment){
        if(Objects.isNull(Utility.getImagesFiles(dataFetchingEnvironment))){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_IMAGES_EMPTY));
        }
        return userService.uploadVehicleImages(vehicleId, Utility.getImagesFiles(dataFetchingEnvironment));
    }
}
