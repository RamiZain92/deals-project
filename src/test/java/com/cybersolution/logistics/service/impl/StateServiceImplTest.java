package com.cybersolution.logistics.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import com.cybersolution.fazeal.logistics.service.impl.StateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.dto.StateDTO;
import com.cybersolution.fazeal.logistics.entity.CountryEntity;
import com.cybersolution.fazeal.logistics.entity.StateEntity;
import com.cybersolution.fazeal.logistics.repository.StateRepository;
import com.cybersolution.fazeal.logistics.service.mapper.StateMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.common.exception.GenericException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StateServiceImplTest {

	@Mock
	private StateRepository stateRepository;
	
	@Mock
	private Messages messages;
	
	@Mock
	private StateMapper stateMapper;

	@InjectMocks
	private StateServiceImpl stateServiceImpl;
	
	@Test
	void testListAllStatesByCountry() {
		CountryEntity countryEntity = CountryEntity.builder().id(1L).build();
		StateEntity stateEntity = StateEntity.builder().countryEntity(countryEntity).id(1L).build();
		StateDTO stateDTO = StateDTO.builder().id(1L).countryId(1L).build();
		when(stateRepository.findByCountryOrderByNameAsc(1L)).thenReturn(List.of(stateEntity));
		when(stateMapper.stateEntityListToStateDTOList(List.of(stateEntity))).thenReturn(List.of(stateDTO));
		List<StateDTO> result = stateServiceImpl.listStatesByCountry(1L);

		assertTrue(result.size() == 1);
	}
	
	@Test
	void testDeleteState() {
		StateEntity stateEntity = StateEntity.builder().id(1L).build();
		when(stateRepository.countById(1L)).thenReturn(1L);
		when(messages.get(AppConstants.STATE_DELETED)).thenReturn("deleted");
		String result = stateServiceImpl.deleteState(1L);

		assertTrue(result.equals("deleted"));
	}
	
	@Test
	void testDeleteNotExistState() {
		when(stateRepository.countById(1L)).thenReturn(0L);
		assertThrows(GenericException.class, () -> {
			stateServiceImpl.deleteState(1L);
		});
	}
	
	@Test
	void testSaveState() {
		StateEntity stateEntity = StateEntity.builder().id(1L).build();
		StateDTO stateDTO = StateDTO.builder().id(1L).build();
		when(stateMapper.stateDTOToStateEntity(stateDTO)).thenReturn(stateEntity);
		when(stateRepository.save(stateEntity)).thenReturn(stateEntity);
		when(messages.get(AppConstants.STATE_SAVED)).thenReturn("saved");
		String result = stateServiceImpl.saveState(stateDTO);

		assertTrue(result.equals("saved"));
	}

}
