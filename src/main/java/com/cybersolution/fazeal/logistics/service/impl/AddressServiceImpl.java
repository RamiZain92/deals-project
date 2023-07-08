package com.cybersolution.fazeal.logistics.service.impl;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.custom.validation.AddressValidation;
import com.cybersolution.fazeal.logistics.dto.UpdateAddressDTO;
import com.cybersolution.fazeal.logistics.entity.AddressEntity;
import com.cybersolution.fazeal.logistics.entity.CountryEntity;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.repository.AddressRepository;
import com.cybersolution.fazeal.logistics.repository.CountryRepository;
import com.cybersolution.fazeal.logistics.service.AddressService;
import com.cybersolution.fazeal.logistics.service.UserService;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressValidation addressValidation;
    @Autowired
    private UserService userService;
    @Autowired
    private Messages messages;
    @Autowired
    private Utility utility;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private AddressRepository addressRepository;
    public MessageResponse updateAddress(UpdateAddressDTO addressDTO){
        addressValidation.isAddressValid(addressDTO);
        UserEntity loggedUser = userService.getLoggedUser();
        CountryEntity countryEntity = countryRepository.findById(addressDTO.getCountryId()).orElse(null);
        if (Objects.isNull(countryEntity)) {
            throw new GenericException(HttpStatus.CONFLICT, AppConstants.COUNTRY_NOT_FOUND,messages.get(AppConstants.COUNTRY_NOT_FOUND));
        }
        utility.zipCodeValidator(addressDTO.getZipCode());
        AddressEntity addressEntity= addressRepository.findById(loggedUser.getAddressEntity().getId()).orElse(null);
        addressEntity.setLocationName(addressDTO.getLocationName());
        addressEntity.setDescription(addressDTO.getDescription());
        addressEntity.setAddressLineOne(addressDTO.getAddressLineOne());
        addressEntity.setAddressLineTwo(addressDTO.getAddressLineTwo());
        addressEntity.setZipCode(addressDTO.getZipCode());
        addressEntity.setCountry(countryEntity);
        addressEntity.setState(addressDTO.getState());
        addressEntity.setCity(addressDTO.getCity());
        addressEntity.setStreetNumber(addressDTO.getStreetNumber());
        addressRepository.save(addressEntity);
        return MessageResponse.builder().message(messages.get(AppConstants.ADDRESS_UPDATED_SUCCESSFULLY)).build();
    }
}
