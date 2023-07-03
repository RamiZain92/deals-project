package com.cybersolution.fazeal.logistics.service.impl;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.common.logistics.dto.UpdateContactNumberDTO;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.repository.UserRepository;
import com.cybersolution.fazeal.logistics.response.UserResponse;
import com.cybersolution.fazeal.logistics.security.services.UserDetailsImpl;
import com.cybersolution.fazeal.logistics.service.UserService;
import com.cybersolution.fazeal.logistics.service.mapper.UserMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Messages messages;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Utility utility;

	@Override
	public UserEntity getLoggedUser() {
		UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		UserEntity userEntity = userRepository.findById(user.getId()).orElse(null);
		if (Objects.isNull(userEntity)){
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.USER_NOT_FOUND,
					messages.get(AppConstants.USER_NOT_FOUND));
		}
		return userEntity;
	}

	@Override
	public UserResponse getLoggedUserDetails() {
		UserEntity userEntity = getLoggedUser();
		return userMapper.userEntityToResponse(userEntity);
	}

	@Override
	public MessageResponse updateContactNumber(UpdateContactNumberDTO updateContactNumberDTO) {
		UserEntity loggedUser = getLoggedUser();
		if(updateContactNumberDTO.getContactNumber().length() <9 || updateContactNumberDTO.getContactNumber().length() >15) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.CONTACT_NO_MUST_BE_09_15_DIGITS));
		}
		if(!utility.isNumberValidator(updateContactNumberDTO.getContactNumber())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.CONTACT_NO_MUST_BE_DIGITS_ONLY));
		}
		loggedUser.setContactNumber(updateContactNumberDTO.getContactNumber());
		userRepository.save(loggedUser);
		return MessageResponse.builder().message(messages.get(AppConstants.CONTACT_NUMBER_UPDATED_SUCCESSFULLY)).build();
	}

}
