package com.cybersolution.fazeal.logistics.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.dto.UpdateAddressDTO;
import com.cybersolution.fazeal.logistics.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

@Service
@Validated
public class AddressMutation implements GraphQLMutationResolver {
    @Autowired
    private AddressService addressService;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateAddress(UpdateAddressDTO updateAddressDTO){
        return addressService.updateAddress(updateAddressDTO);
    }
}
