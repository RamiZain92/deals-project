package com.deals.dto;
import com.deals.constants.AppConstants;
import com.deals.constants.CurrencyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.validation.constraints.NotBlank;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DealDTO {

	private Long id;
	@NotBlank
	private String dealUniqueId;

	@NotBlank
	private CurrencyType fromCurrency;

	@NotBlank
	private CurrencyType toCurrency;

	@NotBlank(message =AppConstants.DEAL_TIME_REQUIRED)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.SIMPLE_DATE_FORMAT)
	private String dealTime;

	@NotBlank(message =AppConstants.DEAL_AMOUNT_REQUIRED)
	private double dealAmount;


}
