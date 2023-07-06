package com.cybersolution.fazeal.logistics.service;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.logistics.dto.UpdateContactNumberDTO;
import com.cybersolution.fazeal.common.logistics.dto.UpdatePasswordDTO;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.response.UserResponse;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface UserService {
	UserEntity getLoggedUser();

	public UserResponse getLoggedUserDetails();
	MessageResponse updateContactNumber(UpdateContactNumberDTO updateContactNumberDTO);
	MessageResponse updatePassword(UpdatePasswordDTO changePasswordDTO);
	MessageResponse updateEmail(String email);
	MessageResponse updateProfileImage(String profileImageUrl);
	MessageResponse updateLicenceNumberImage(String licenceNumberImageUrl);
	MessageResponse updateIdImage(String idImageUrl);
	MessageResponse uploadVehicleImages(Long vehicleId,  List<CommonsMultipartFile> images);
}
