package com.cybersolution.fazeal.logistics.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersolution.fazeal.logistics.dto.CityResponse;
import com.cybersolution.fazeal.logistics.entity.CityEntity;

public class CityMapper {
	public static List<CityResponse> convertToCityResponse(List<CityEntity> city) {
		return city.stream().map(cityy -> convertCityToCityResponse(cityy)).collect(Collectors.toList());
	}

	public static CityResponse convertCityToCityResponse(CityEntity city) {
		CityResponse cityR = CityResponse.builder().id(city.getId()).name(city.getName())
				.build();
		return cityR;
	}
}
