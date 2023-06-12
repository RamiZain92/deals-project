package com.cybersolution.fazeal.logistics.service;

import java.util.List;

import com.cybersolution.fazeal.logistics.dto.CityResponse;

public interface CityService {
	 public List<CityResponse> getCitiesByState(Long stateId);
}
