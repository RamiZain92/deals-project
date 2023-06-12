package com.cybersolution.fazeal.logistics.service.impl;
import java.util.List;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.dto.StateDTO;
import com.cybersolution.fazeal.logistics.repository.StateRepository;
import com.cybersolution.fazeal.logistics.service.mapper.StateMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cybersolution.fazeal.logistics.service.StateService;
import com.cybersolution.fazeal.common.exception.GenericException;

@Service
public class StateServiceImpl implements StateService{
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private StateMapper stateMapper;
	
	@Autowired
	private Messages messages;

	@Override
	public List<StateDTO> listStatesByCountry(Long countryId) {
		return stateMapper.stateEntityListToStateDTOList(stateRepository.findByCountryOrderByNameAsc(countryId));
	}

	@Override
	public String deleteState(Long id) {
		if(stateRepository.countById(id) != 1) {
			throw new GenericException(HttpStatus.NOT_FOUND, String.valueOf(HttpStatus.NOT_FOUND.value()) , messages.get(AppConstants.STATE_NOT_FOUND) + id);
		}
		stateRepository.deleteById(id);
		return messages.get(AppConstants.STATE_DELETED);
	}

	@Override
	public String saveState(StateDTO stateDTO) {
		stateRepository.save(stateMapper.stateDTOToStateEntity(stateDTO));
		return messages.get(AppConstants.STATE_SAVED);
	}

}
