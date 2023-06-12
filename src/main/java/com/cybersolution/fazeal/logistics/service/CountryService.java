package com.cybersolution.fazeal.logistics.service;

import java.util.List;

import com.cybersolution.fazeal.logistics.dto.CountryDTO;

public interface CountryService {
	
	public List<CountryDTO> listAllCountries();
	
	public String deleteCountry(Long id);
	
	public String saveCountry(CountryDTO countryDTO);

	public CountryDTO getCountry(Long id);
}
