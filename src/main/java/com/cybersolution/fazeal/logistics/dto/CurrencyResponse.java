package com.cybersolution.fazeal.logistics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class CurrencyResponse {

	private Long id;

	private String code;

	private String displayName;

	private String symbol;

}
