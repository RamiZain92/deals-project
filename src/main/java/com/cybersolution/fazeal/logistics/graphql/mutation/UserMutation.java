package com.cybersolution.fazeal.logistics.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.common.logistics.dto.UpdateContactNumberDTO;
import com.cybersolution.fazeal.common.logistics.dto.UpdatePasswordDTO;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.service.UserService;
import com.cybersolution.fazeal.logistics.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Service
@Validated
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private Messages messages;

    @Autowired
    private HttpServletResponse response;
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateContactNumber(@Valid UpdateContactNumberDTO updateContactNumberDTO){
        return userService.updateContactNumber(updateContactNumberDTO);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updatePassword(@Valid UpdatePasswordDTO updatePasswordDTO){
        return userService.updatePassword(updatePasswordDTO);
    }
    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public MessageResponse updateEmail(String email){
        return userService.updateEmail(email);
    }
}
