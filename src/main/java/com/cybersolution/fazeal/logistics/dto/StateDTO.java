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
@Getter
@Builder
public class StateDTO {
	
	private Long id;

	@NotBlank(message = AppConstants.STATEID_BLANK)
	private String name;
	
	private Long countryId;

}
