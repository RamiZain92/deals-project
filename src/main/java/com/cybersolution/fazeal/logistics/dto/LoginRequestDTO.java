package com.cybersolution.fazeal.logistics.dto;

import javax.validation.constraints.NotBlank;

import com.cybersolution.fazeal.logistics.constants.AppConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginRequestDTO {
	@NotBlank(message = AppConstants.USER_NAME_EMPTY)
	private String userName;

	@NotBlank(message = AppConstants.PASSWORD_NOT_EMPTY)
	private String password;

}
