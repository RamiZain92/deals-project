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
    @NotNull(message = AppConstants.ADDRESS_DESCRIPTION_REQUIRED)
    private String description;
    @NotNull(message = AppConstants.ADDRESSLINEONE_BLANK)
    private String addressLineOne;
    private String addressLineTwo;
    @NotNull(message = AppConstants.POSTAL_CODE_BLANK)
    private String zipCode;
    @NotNull(message = AppConstants.COUNTRYID_BLANK)
    private Long countryId;
    @NotNull(message = AppConstants.STATEID_BLANK)
    private String state;
    @NotNull(message = AppConstants.CITY_BLANK)
    private String city;
    @NotNull(message = AppConstants.STREET_NUMBER_REQUIRED)
    private String streetNumber;
}
