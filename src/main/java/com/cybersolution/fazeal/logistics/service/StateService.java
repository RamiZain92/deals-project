package com.cybersolution.fazeal.logistics.service;

import java.util.List;

import com.cybersolution.fazeal.logistics.dto.StateDTO;

public interface StateService {
	
	public List<StateDTO> listStatesByCountry(Long countryId);
	
	public String deleteState(Long id);
	
	public String saveState(StateDTO stateDTO);

}
