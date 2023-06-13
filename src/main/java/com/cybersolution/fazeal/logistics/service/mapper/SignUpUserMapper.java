package com.cybersolution.fazeal.logistics.service.mapper;

import com.amazonaws.services.rekognition.model.GenderType;
import com.cybersolution.fazeal.logistics.dto.AddressDTO;
import com.cybersolution.fazeal.logistics.dto.SignUpUserDTO;
import com.cybersolution.fazeal.logistics.dto.VehicleDetailsDTO;
import com.cybersolution.fazeal.logistics.entity.AddressEntity;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.constants.SignUpProcess;
import com.cybersolution.fazeal.logistics.constants.Status;
import com.cybersolution.fazeal.logistics.entity.VehicleEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class SignUpUserMapper {
	
	@Autowired
	private PasswordEncoder encoder;

	@SneakyThrows
	public UserEntity dtoToEntity(SignUpUserDTO signUpUserDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setActive(Status.ACTIVE);
		userEntity.setUserName(signUpUserDTO.getUsername());
		userEntity.setMiddleName(signUpUserDTO.getMiddleName());
		userEntity.setFirstName(signUpUserDTO.getFirstName());
		userEntity.setLastName(signUpUserDTO.getLastName());
		userEntity.setGenderType(GenderType.valueOf(signUpUserDTO.getGenderType()));
		userEntity.setEmail(signUpUserDTO.getEmail());
		userEntity.setPassword(encoder.encode(signUpUserDTO.getPassword()));
		userEntity.setContactNumber(signUpUserDTO.getContactNumber());
		userEntity.setCreatedDate(LocalDateTime.now());
		userEntity.setUserIdImageUrl(signUpUserDTO.getUserIDImagePath());
		userEntity.setUserLicenceNumberImageUrl(signUpUserDTO.getUserLicenseNumberImage());
		userEntity.setUserPersonalImageUrl(signUpUserDTO.getUserPersonalImage());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(signUpUserDTO.getDateOfBirth(), formatter);
		userEntity.setDateOfBirth(date);
		userEntity.setAvailability(false);
		userEntity.setEnabled(false);
		userEntity.setSignUpProcess(SignUpProcess.NOT_DONE);
		return userEntity;
	}

	public VehicleEntity vehicleDtoToEntity(VehicleDetailsDTO vehicleDetailsDTO) {
		VehicleEntity vehicleEntity = new VehicleEntity();
		vehicleEntity.setActive(Status.INACTIVE);
		vehicleEntity.setType(vehicleDetailsDTO.getType());
		vehicleEntity.setLicensePlate(vehicleDetailsDTO.getLicensePlateNumber());
		vehicleEntity.setBrand(vehicleDetailsDTO.getBrand());
		vehicleEntity.setModelName(vehicleDetailsDTO.getModelName());
		vehicleEntity.setCapacity(vehicleDetailsDTO.getCapacity());
		vehicleEntity.setFuelType(vehicleDetailsDTO.getFuelType());
		vehicleEntity.setMileage(vehicleDetailsDTO.getMileAge());
		vehicleEntity.setYear(vehicleDetailsDTO.getYear());
		vehicleEntity.setStatus(vehicleDetailsDTO.getStatus());
		vehicleEntity.setDrivingMethod(vehicleDetailsDTO.getDrivingMethod());
		vehicleEntity.setColor(vehicleDetailsDTO.getColor());
		vehicleEntity.setEnabled(false);
		return vehicleEntity;
	}

	public AddressEntity addressDtoToEntity(AddressDTO addressDTO) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setActive(Status.ACTIVE);
		addressEntity.setLocationName(addressDTO.getLocationName());
		addressEntity.setAddressLineOne(addressDTO.getAddressLineOne());
		addressEntity.setAddressLineTwo(addressDTO.getAddressLineTwo());
		addressEntity.setDescription(addressDTO.getDescription());
		addressEntity.setState(addressDTO.getState());
		addressEntity.setCity(addressDTO.getCity());
		addressEntity.setZipCode(addressDTO.getZipCode());
		addressEntity.setStreetNumber(addressDTO.getStreetNumber());
		return addressEntity;
	}
	
}
