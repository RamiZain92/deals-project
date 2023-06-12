package com.cybersolution.logistics.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import com.cybersolution.fazeal.logistics.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.dto.CountryDTO;
import com.cybersolution.fazeal.logistics.entity.CountryEntity;
import com.cybersolution.fazeal.logistics.repository.CountryRepository;
import com.cybersolution.fazeal.logistics.service.mapper.CountryMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.common.exception.GenericException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CountryServiceImplTest {

	@Mock
	private CountryRepository countryRepository;
	
	@Mock
	private Messages messages;
	
	@Mock
	private CountryMapper countryMapper;

	@InjectMocks
	private CountryServiceImpl countryServiceImpl;
	
	@Test
	void testListAllCountries() {
		CountryEntity countryEntity = CountryEntity.builder().id(1L).build();
		CountryDTO countryDTO = CountryDTO.builder().id(1L).build();
		when(countryRepository.findAllByOrderByNameAsc()).thenReturn(List.of(countryEntity));
		when(countryMapper.countryEntityListToCountryDTOList(List.of(countryEntity))).thenReturn(List.of(countryDTO));
		List<CountryDTO> result = countryServiceImpl.listAllCountries();

		assertTrue(result.size() == 1);
	}
	
	@Test
	void testDeleteCountry() {
		CountryEntity countryEntity = CountryEntity.builder().id(1L).build();
		CountryDTO countryDTO = CountryDTO.builder().id(1L).build();
		when(countryRepository.countById(1L)).thenReturn(1L);
		when(messages.get(AppConstants.COUNTRY_DELETED)).thenReturn("deleted");
		String result = countryServiceImpl.deleteCountry(1L);

		assertTrue(result.equals("deleted"));
	}
	
	@Test
	void testDeleteNotExistCountry() {
		when(countryRepository.countById(1L)).thenReturn(0L);
		assertThrows(GenericException.class, () -> {
			countryServiceImpl.deleteCountry(1L);
		});
	}
	
	@Test
	void testSaveCountry() {
		CountryEntity countryEntity = CountryEntity.builder().id(1L).build();
		CountryDTO countryDTO = CountryDTO.builder().id(1L).build();
		when(countryMapper.countryDTOToCountryEntity(countryDTO)).thenReturn(countryEntity);
		when(countryRepository.save(countryEntity)).thenReturn(countryEntity);
		when(messages.get(AppConstants.COUNTRY_SAVED)).thenReturn("saved");
		String result = countryServiceImpl.saveCountry(countryDTO);

		assertTrue(result.equals("saved"));
	}
	
	

}
