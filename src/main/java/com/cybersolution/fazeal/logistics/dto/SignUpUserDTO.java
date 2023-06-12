package com.cybersolution.fazeal.logistics.dto;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SignUpUserDTO {

    @NotBlank(message = AppConstants.FIRST_NAME_BLANK)
    private String firstName;

    @NotBlank(message = AppConstants.LAST_NAME_BLANK)
    private String lastName;

    private String middleName;

    @NotBlank(message = AppConstants.GENDER_TYPE_BLANK)
    private String genderType;

    @NotBlank(message = AppConstants.USER_NAME_INVALID)
    private String username;

    @NotBlank(message = AppConstants.EMAIL_NOT_BLANK)
    private String email;

    @NotBlank(message = AppConstants.PASSWORD_EMPTY)
    private String password;

    @NotBlank(message = AppConstants.MOBILE_NUMBER_BLANK)
    private String contactNumber;

    private String userIDImagePath;

    private String userLicenseNumberImage;

    private String userPersonalImage;

    @NotNull(message = AppConstants.LAST_NAME_BLANK)
//    @Past(message = AppConstants.INVALID_DATE_OF_BIRTH)
    private String dateOfBirth;

}
