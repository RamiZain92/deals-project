package com.cybersolution.fazeal.logistics.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.cybersolution.fazeal.logistics.dto.StateDTO;
import com.cybersolution.fazeal.logistics.entity.StateEntity;
import com.cybersolution.fazeal.logistics.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<StateDTO> stateEntityListToStateDTOList(List<StateEntity> stateEntities) {
		return stateEntities.stream()
				.map((stateEntity) -> stateEntityToStateDTO(stateEntity)).collect(Collectors.toList());
	}

	public StateDTO stateEntityToStateDTO(StateEntity stateEntity) {
		StateDTO stateDTO = new StateDTO();
		stateDTO.setId(stateEntity.getId());
		stateDTO.setName(stateEntity.getName());
		stateDTO.setCountryId(stateEntity.getCountryEntity().getId());
		return stateDTO;
	}
	
	public StateEntity stateDTOToStateEntity(StateDTO stateDTO) {
		StateEntity stateEntity = new StateEntity();
		stateEntity.setId(stateDTO.getId());
		stateEntity.setName(stateDTO.getName());
		stateEntity.setCountryEntity(countryRepository.findById(stateDTO.getCountryId()).orElse(null));
		return stateEntity;
	}

}
