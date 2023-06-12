package com.cybersolution.fazeal.logistics.service.impl;

import java.util.List;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.dto.CityResponse;
import com.cybersolution.fazeal.logistics.entity.CityEntity;
import com.cybersolution.fazeal.logistics.entity.StateEntity;
import com.cybersolution.fazeal.logistics.repository.CityRepository;
import com.cybersolution.fazeal.logistics.repository.StateRepository;
import com.cybersolution.fazeal.logistics.service.mapper.CityMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cybersolution.fazeal.logistics.service.CityService;
import com.cybersolution.fazeal.common.exception.GenericException;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private Messages messages;

	@Override
	public List<CityResponse> getCitiesByState(Long stateId) {
		StateEntity state = stateRepository.findById(stateId)
				.orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, AppConstants.STATE_NOT_FOUND,
						messages.get(AppConstants.STATE_NOT_FOUND)));
		List<CityEntity> city = cityRepository.findByStateEntity(state);
		return CityMapper.convertToCityResponse(city);
	}

}
