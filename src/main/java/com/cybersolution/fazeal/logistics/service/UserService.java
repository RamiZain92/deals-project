package com.cybersolution.fazeal.logistics.service;

import com.cybersolution.fazeal.common.logistics.dto.UpdateContactNumberDTO;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.response.UserResponse;

public interface UserService {
	UserEntity getLoggedUser();

	public UserResponse getLoggedUserDetails();
	String updateContactNumber(UpdateContactNumberDTO updateContactNumberDTO);

}
