package com.cybersolution.fazeal.logistics.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersolution.fazeal.logistics.dto.CountryDTO;
import com.cybersolution.fazeal.logistics.entity.CountryEntity;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
	
	public List<CountryDTO> countryEntityListToCountryDTOList(List<CountryEntity> countryEntities) {
		return countryEntities.stream()
				.map((countryEntity) -> countryEntityToCountryDTO(countryEntity)).collect(Collectors.toList());
	}

	public CountryDTO countryEntityToCountryDTO(CountryEntity countryEntity) {
		CountryDTO countryDTO = new CountryDTO();
		countryDTO.setId(countryEntity.getId());
		countryDTO.setName(countryEntity.getName());
		countryDTO.setCode(countryEntity.getCode());
		return countryDTO;
	}
	
	public CountryEntity countryDTOToCountryEntity(CountryDTO countryDTO) {
		CountryEntity countryEntity = new CountryEntity();
		countryEntity.setId(countryDTO.getId());
		countryEntity.setName(countryDTO.getName());
		countryEntity.setCode(countryDTO.getCode());
		return countryEntity;
	}

}
