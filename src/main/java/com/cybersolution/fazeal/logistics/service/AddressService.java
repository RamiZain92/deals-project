package com.cybersolution.fazeal.logistics.service;

import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.logistics.dto.UpdateAddressDTO;

public interface AddressService {
    MessageResponse updateAddress(UpdateAddressDTO addressDTO);
}
