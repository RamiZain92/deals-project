package com.cybersolution.fazeal.logistics.custom.validation;

import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.dto.UpdateAddressDTO;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class AddressValidation {
    @Autowired
    private Utility utility;
    @Autowired
    private Messages messages;
    public void isAddressValid(UpdateAddressDTO updateAddressDTO){
        if(Objects.isNull(updateAddressDTO.getDescription())){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.ADDRESS_DESCRIPTION_REQUIRED));
        }
        if(Objects.isNull(updateAddressDTO.getAddressLineOne())){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.ADDRESSLINEONE_BLANK));
        }
        if(Objects.isNull(updateAddressDTO.getZipCode())){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.POSTAL_CODE_BLANK));
        }
        if(Objects.isNull(updateAddressDTO.getCity())){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.CITY_BLANK));
        }
        if(Objects.isNull(updateAddressDTO.getCountryId())){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.COUNTRYID_BLANK));
        }
        if(Objects.isNull(updateAddressDTO.getState())){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.STATEID_BLANK));
        }
        if(Objects.isNull(updateAddressDTO.getStreetNumber())){
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.VALIDATION_FAILED,
                    messages.get(AppConstants.STREET_NUMBER_REQUIRED));
        }
    }
}
