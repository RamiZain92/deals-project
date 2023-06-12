package com.cybersolution.fazeal.logistics.response;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private Long userId;
    private Long vehicleId;
    private Long addressId;
    private String message;
}
