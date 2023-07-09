package com.cybersolution.fazeal.logistics.dto;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import lombok.*;

import javax.validation.constraints.NotNull;
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressDTO {
    private String locationName;
    private String description;
    private String addressLineOne;
    private String addressLineTwo;
    private String zipCode;
    private Long countryId;
    private String state;
    private String city;
    private String streetNumber;
}
