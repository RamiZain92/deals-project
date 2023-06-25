package com.cybersolution.fazeal.logistics.response;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Long id;
    private String locationName;
    private String description;
    private String addressLineOne;
    private String addressLineTwo;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String streetNumber;
}
