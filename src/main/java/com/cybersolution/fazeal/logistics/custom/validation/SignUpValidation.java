package com.cybersolution.fazeal.logistics.custom.validation;

import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.exception.GenericObjectException;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.constants.DrivingMethod;
import com.cybersolution.fazeal.logistics.constants.GenderType;
import com.cybersolution.fazeal.logistics.dto.SignUpUserDTO;
import com.cybersolution.fazeal.logistics.dto.VehicleDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SignUpValidation {
	
	@Autowired
	private Utility utility;
	@Autowired
	private Messages messages;
	
	public void createUserInfoValidation(SignUpUserDTO signUpUserDTO) {
		
		if(signUpUserDTO.getUsername().length() > 18) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.USER_NAME_INVALID));
		}
		
		if(utility.isNumberValidator(signUpUserDTO.getUsername())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.USER_NAME_INVALID));
		}
		
		if(!utility.isUserValidator(signUpUserDTO.getUsername())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.USER_NAME_INVALID));
		}
		
		if(signUpUserDTO.getFirstName().length() < 3) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_FIRSTNAME));
		}
		
		if(signUpUserDTO.getFirstName().length() > 12) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_FIRSTNAME));
		}
		
		if(utility.isSpacialCharacterValidator(signUpUserDTO.getFirstName())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_FIRSTNAME));
		}
		
		if(signUpUserDTO.getLastName().length() < 3) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_LASTNAME));
		}
		
		if(signUpUserDTO.getLastName().length() > 12) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_LASTNAME));
		}
		
		if(utility.isSpacialCharacterValidator(signUpUserDTO.getLastName())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_LASTNAME));
		}

		if(Objects.nonNull(signUpUserDTO.getMiddleName())){
			if(signUpUserDTO.getLastName().length() < 3) {
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_MIDDLENAME));
			}

			if(signUpUserDTO.getLastName().length() > 12) {
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_MIDDLENAME));
			}

			if(utility.isSpacialCharacterValidator(signUpUserDTO.getLastName())) {
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.NOT_USED_SPECIAL_CHARACTER_OR_NUMBER_MIDDLENAME));
			}
		}

		if(!GenderType.FEMALE.toString().equals(signUpUserDTO.getGenderType()) && !GenderType.MALE.toString().equals(signUpUserDTO.getGenderType())){
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.INVALID_GENDER_TYPE));
		}
		
		if(!utility.isPasswordValidator(signUpUserDTO.getPassword())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.PASSWORD_NOT_EMPTY));
		}
		
		if(!utility.isEmailValid(signUpUserDTO.getEmail())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.EMAIL_INVALID));
		}
		
		if(!utility.isMobileNoValid(signUpUserDTO.getContactNumber())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.MOBILE_NO_MUST_BE_10_15_DIGITS));
		}
		
//		if(signUpUserDTO.getDlOrSSL().length() < 7) {
//			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.DLORSSL_INVALID_NUMBER));
//		}
//		
//		if(signUpUserDTO.getDlOrSSL().length() > 16) {
//			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.DLORSSL_INVALID_NUMBER));
//		}
//		
//		if(!utility.isSSLValidator(signUpUserDTO.getDlOrSSL()) && !utility.isSSL2Validator(signUpUserDTO.getDlOrSSL())) {
//			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.DLORSSL_INVALID_NUMBER));
//		}
//		
//		
//		if (signUpUserDTO.getUploadYourID().getSize() < 1) {
//			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.UPLOAD_ID_NOT_BLANK));
//		}
//		
//		
//		
//		if (Objects.nonNull(signUpUserDTO.getUploadYourID()) && (signUpUserDTO.getUploadYourID().getSize() > 0)) {
//			String fileName = signUpUserDTO.getUploadYourID().getOriginalFilename();
//			if(!utility.isImageValidator(fileName)) {
//				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.INVALID_IMG_TYPE));
//			}
//		}
	}

	public void createVehicleInfoValidation(VehicleDetailsDTO vehicleDetailsDTO) {

		if(!vehicleDetailsDTO.getDrivingMethod().equals(DrivingMethod.BICYCLE) && !vehicleDetailsDTO.getDrivingMethod().equals(DrivingMethod.CAR)
		  && !vehicleDetailsDTO.getDrivingMethod().equals(DrivingMethod.VAN) && !vehicleDetailsDTO.getDrivingMethod().equals(DrivingMethod.ON_FOOT)){
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.DRIVING_TYPE_INVALID));
		}

		if(!vehicleDetailsDTO.getDrivingMethod().equals(DrivingMethod.ON_FOOT)){
			if(Objects.isNull(vehicleDetailsDTO.getBrand())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_BRAND_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getType())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_TYPE_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getLicensePlateNumber())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.LICENSE_PLATE_NUMBER_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getModelName())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_MODEL_NAME_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getCapacity())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_CAPACITY_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getFuelType())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_FUEL_TYPE_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getMileAge())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_MILE_AGE_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getYear())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_YEAR_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getStatus())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_STATUS_REQUIRED));
			}
			if(Objects.isNull(vehicleDetailsDTO.getColor())){
				throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_COLOR_REQUIRED));
			}
		}


	}
}
