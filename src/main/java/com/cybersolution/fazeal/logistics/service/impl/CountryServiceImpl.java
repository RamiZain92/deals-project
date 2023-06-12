package com.cybersolution.fazeal.logistics.service.impl;

import java.util.List;
import java.util.Objects;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.dto.CountryDTO;
import com.cybersolution.fazeal.logistics.entity.CountryEntity;
import com.cybersolution.fazeal.logistics.repository.CountryRepository;
import com.cybersolution.fazeal.logistics.service.mapper.CountryMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cybersolution.fazeal.logistics.service.CountryService;
import com.cybersolution.fazeal.common.exception.GenericException;

import lombok.SneakyThrows;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CountryMapper countryMapper;
	
	@Autowired
	private Messages messages;

	@Override
	public List<CountryDTO> listAllCountries() {
		return countryMapper.countryEntityListToCountryDTOList(countryRepository.findAllByOrderByNameAsc());
	}

	@SneakyThrows
	@Override
	public String deleteCountry(Long id) {
		if(countryRepository.countById(id) != 1) {
			throw new GenericException(HttpStatus.NOT_FOUND, String.valueOf(HttpStatus.NOT_FOUND.value()) , messages.get(AppConstants.COUNTRY_NOT_FOUND) + id);
		}
		countryRepository.deleteById(id);
		return messages.get(AppConstants.COUNTRY_DELETED);
	}

	@Override
	public String saveCountry(CountryDTO countryDTO) {
		countryRepository.save(countryMapper.countryDTOToCountryEntity(countryDTO));
		return messages.get(AppConstants.COUNTRY_SAVED);
	}

	@SneakyThrows
	@Override
	public CountryDTO getCountry(Long id) {
		CountryEntity countryEntity = countryRepository.findById(id).orElse(null);
		if(Objects.isNull(countryEntity)) {
			throw new GenericException(HttpStatus.NOT_FOUND, String.valueOf(HttpStatus.NOT_FOUND.value()) , messages.get(AppConstants.COUNTRY_NOT_FOUND) + id);			
		}
		return countryMapper.countryEntityToCountryDTO(countryEntity);
	}

}
