package com.cybersolution.fazeal.logistics.service.impl;

import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.constants.*;
import com.cybersolution.fazeal.logistics.custom.validation.SignUpValidation;
import com.cybersolution.fazeal.logistics.dto.AddressDTO;
import com.cybersolution.fazeal.logistics.entity.*;
import com.cybersolution.fazeal.logistics.repository.*;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.dto.SignUpUserDTO;
import com.cybersolution.fazeal.logistics.dto.VehicleDetailsDTO;
import com.cybersolution.fazeal.logistics.response.SignUpResponse;
import com.cybersolution.fazeal.logistics.service.SignUpService;
import com.cybersolution.fazeal.logistics.service.mapper.SignUpUserMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private SignUpValidation signUpValidation;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private Messages messages;

	@Autowired
	private SignUpUserMapper signUpUserMapper;

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public SignUpResponse createUserInfo(SignUpUserDTO signUpUserDTO) {
		signUpValidation.createUserInfoValidation(signUpUserDTO);
		UserEntity userEntity = userRepository.findByUserName(signUpUserDTO.getUsername()).orElse(null);
		if (Objects.nonNull(userEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.USER_NAME_ALREADY_EXIST,messages.get(AppConstants.USER_NAME_ALREADY_EXIST));
		}
		userEntity = userRepository.findByEmail(signUpUserDTO.getEmail()).orElse(null);
		if (Objects.nonNull(userEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.EMAIL_ALREADY_EXIST,messages.get(AppConstants.EMAIL_ALREADY_EXIST));
		}
		userEntity = userRepository.findByContactNumber(signUpUserDTO.getContactNumber()).orElse(null);
		if (Objects.nonNull(userEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.MOBILE_NUMBER_ALREADY_EXIST,messages.get(AppConstants.MOBILE_NUMBER_ALREADY_EXIST));
		}
		userEntity = signUpUserMapper.dtoToEntity(signUpUserDTO);
		UserEntity savedUser = userRepository.save(userEntity);

		RoleEntity roleEntity = roleRepository.findByRoleType(RoleType.ROLE_USER).orElse(null);
		userEntity.setRoleEntity(roleEntity);
		userRepository.save(userEntity);
		
		return SignUpResponse.builder().userId(savedUser.getId()).message(messages.get(AppConstants.SUCCESS_MESSAGE_USER_INFO)).build();
	}

	@Override
	public SignUpResponse createVehicleDetails(VehicleDetailsDTO vehicleDetailsDTO) {
		signUpValidation.createVehicleInfoValidation(vehicleDetailsDTO);
		UserEntity userEntity = userRepository.findById(vehicleDetailsDTO.getUserId()).orElse(null);
		if (Objects.isNull(userEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.USER_NOT_FOUND,messages.get(AppConstants.USER_NOT_FOUND));
		}
		if(userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW) || userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW)){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.DRIVER_ALREADY_REGISTERED,messages.get(AppConstants.DRIVER_ALREADY_REGISTERED));
		}
		VehicleEntity vehicleEntity = signUpUserMapper.vehicleDtoToEntity(vehicleDetailsDTO);
		vehicleEntity.setUserEntity(userEntity);
		VehicleEntity savedVehicle = vehicleRepository.save(vehicleEntity);
		userEntity.getVehicleEntities().add(savedVehicle);
		userRepository.save(userEntity);
		return SignUpResponse.builder().userId(userEntity.getId()).vehicleId(savedVehicle.getId()).message(messages.get(AppConstants.SUCCESS_MESSAGE_VEHICLE_DETAILS)).build();
	}

	@Override
	public SignUpResponse uploadVehicleImages(Long userId, Long vehicleId, List<String> images) {
		if(Objects.isNull(userId)){
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.USER_ID_REQUIRED));
		}
		if(Objects.isNull(vehicleId)){
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.VEHICLE_ID_REQUIRED));
		}
		UserEntity userEntity = userRepository.findById(userId).orElse(null);
		if (Objects.isNull(userEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.USER_NOT_FOUND,messages.get(AppConstants.USER_NOT_FOUND));
		}
		if(userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW) || userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW)){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.DRIVER_ALREADY_REGISTERED,messages.get(AppConstants.DRIVER_ALREADY_REGISTERED));
		}
		VehicleEntity vehicleEntity = vehicleRepository.findById(vehicleId).orElse(null);
		if(Objects.isNull(vehicleEntity)){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.VEHICLE_NOT_FOUND,messages.get(AppConstants.VEHICLE_NOT_FOUND));
		}
		if(!vehicleEntity.getUserEntity().getId().equals(userEntity.getId())){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.VEHICLE_NOT_FOUND,messages.get(AppConstants.VEHICLE_NOT_FOUND));
		}
		for (String image: images){
			VehicleImagesEntity vehicleImagesEntity = VehicleImagesEntity.builder()
					.vehicleEntity(vehicleEntity)
					.imagePath(image).build();
			vehicleEntity.getVehicleImagesEntities().add(vehicleImagesEntity);
		}
		vehicleRepository.save(vehicleEntity);
		return SignUpResponse.builder().userId(userEntity.getId()).vehicleId(vehicleEntity.getId()).message(messages.get(AppConstants.SUCCESS_MESSAGE_VEHICLE_IMAGES)).build();
	}

	@Override
	public SignUpResponse createAddressInfo(AddressDTO addressDTO) {
		UserEntity userEntity = userRepository.findById(addressDTO.getUserId()).orElse(null);
		if (Objects.isNull(userEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.USER_NOT_FOUND,messages.get(AppConstants.USER_NOT_FOUND));
		}
		if(userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW) || userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW)){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.DRIVER_ALREADY_REGISTERED,messages.get(AppConstants.DRIVER_ALREADY_REGISTERED));
		}
		CountryEntity countryEntity = countryRepository.findById(addressDTO.getCountryId()).orElse(null);
		if (Objects.isNull(countryEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.COUNTRY_NOT_FOUND,messages.get(AppConstants.COUNTRY_NOT_FOUND));
		}
		AddressEntity addressEntity = signUpUserMapper.addressDtoToEntity(addressDTO);
		addressEntity.setCountry(countryEntity);
		addressEntity.setUserEntity(userEntity);
		AddressEntity savedAddress = addressRepository.save(addressEntity);
		userEntity.setAddressEntity(savedAddress);
		userRepository.save(userEntity);
		return SignUpResponse.builder().userId(userEntity.getId()).addressId(savedAddress.getId()).message(messages.get(AppConstants.SUCCESS_MESSAGE_ADDRESS_DETAILS)).build();
	}

	@Override
	public SignUpResponse agreements(Long userId) {
		if(Objects.isNull(userId)){
			throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,messages.get(AppConstants.USER_ID_REQUIRED));
		}
		UserEntity userEntity = userRepository.findById(userId).orElse(null);
		if (Objects.isNull(userEntity)) {
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.USER_NOT_FOUND,messages.get(AppConstants.USER_NOT_FOUND));
		}
		if(userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW) || userEntity.getSignUpProcess().equals(SignUpProcess.UNDER_REVIEW)){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.DRIVER_ALREADY_REGISTERED,messages.get(AppConstants.DRIVER_ALREADY_REGISTERED));
		}
		if(userEntity.getVehicleEntities().size() == 0){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.VEHICLES_EMPTY,messages.get(AppConstants.VEHICLES_EMPTY));
		}
//		userEntity.getVehicleEntities().forEach(vehicle -> {
//			if(vehicle.getVehicleImagesEntities().size() == 0){
//				throw new GenericException(HttpStatus.CONFLICT, AppConstants.USER_NAME_ALREADY_EXIST,messages.get(AppConstants.USER_NAME_ALREADY_EXIST));
//			}
//		});
		if(Objects.isNull(userEntity.getAddressEntity())){
			throw new GenericException(HttpStatus.CONFLICT, AppConstants.ADDRESS_EMPTY,messages.get(AppConstants.ADDRESS_EMPTY));
		}
		userEntity.setAgreements(Agreements.DONE);
		userEntity.setPolicies(Policies.DONE);
		userEntity.setSignUpProcess(SignUpProcess.UNDER_REVIEW);
		userRepository.save(userEntity);
		return SignUpResponse.builder().userId(userEntity.getId()).message(messages.get(AppConstants.SUCCESS_MESSAGE_AGREEMENTS_DONE)).build();
	}


}
