package com.cybersolution.fazeal.logistics.service;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.logistics.dto.UpdateContactNumberDTO;
import com.cybersolution.fazeal.common.logistics.dto.UpdatePasswordDTO;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.response.UserResponse;

public interface UserService {
	UserEntity getLoggedUser();

	public UserResponse getLoggedUserDetails();
	MessageResponse updateContactNumber(UpdateContactNumberDTO updateContactNumberDTO);
	MessageResponse updatePassword(UpdatePasswordDTO changePasswordDTO);
}
