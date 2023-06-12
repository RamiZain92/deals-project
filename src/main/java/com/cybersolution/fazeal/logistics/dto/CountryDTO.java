package com.cybersolution.fazeal.logistics.dto;

import javax.validation.constraints.NotBlank;

import com.cybersolution.fazeal.logistics.constants.AppConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper=false,onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class CountryDTO {
	
	private Long id;

	@NotBlank(message = AppConstants.COUNTRY_NAME_BLANK)
	private String name;
	
	@NotBlank(message = AppConstants.COUNTRY_CODE_BLANK)
	private String code;

}
