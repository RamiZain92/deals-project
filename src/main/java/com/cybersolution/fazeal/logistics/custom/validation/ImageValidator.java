package com.cybersolution.fazeal.logistics.custom.validation;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import com.cybersolution.fazeal.common.exception.GenericException;

@Component
public class ImageValidator {

	@Autowired
	private Messages messages;
	@Autowired
	private Utility utility;
	
	public void imageValidation(MultipartFile imageFile) {
		
		if (Objects.isNull(imageFile)) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.IMAGE_CANT_BE_BLANK));
		}
		
		if (Objects.nonNull(imageFile) && (imageFile.getSize() > 0)) {
			String fileName = imageFile.getOriginalFilename();
			if(!utility.isImageValidator(fileName)) {
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.INVALID_IMG_TYPE));
			}
		}
	}

}
