package com.cybersolution.fazeal.logistics.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.response.UserResponse;
import com.cybersolution.fazeal.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    @PreAuthorize(value = AppConstants.HAS_ADMIN_ROLE_OR_USER_ROLE)
    public UserResponse getLoggedUserDetails(){
        return userService.getLoggedUserDetails();
    }
}
