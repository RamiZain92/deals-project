package com.cybersolution.fazeal.logistics.dto;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenRefreshRequestDTO {
	
    @NotBlank(message = AppConstants.REFRESH_TOKEN_NOT_EMPTY)
    private String refreshToken;
}
