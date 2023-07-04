package com.cybersolution.fazeal.logistics.response;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String firstName;
    private String lastName;
    private String genderType;
    private String userName;
    private String email;
    private String countryCode;
    private String contactNumber;
    private String userIdImageUrl;
    private String userLicenceNumberImageUrl;
    private String userPersonalImageUrl;
    private Boolean availability;
    private String signUpProcess;
    private Boolean enabled;
    private String dateOfBirth;
    private AddressResponse addressResponse;
}
