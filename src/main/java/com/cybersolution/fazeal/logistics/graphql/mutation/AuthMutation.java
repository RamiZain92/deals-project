package com.cybersolution.fazeal.logistics.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.cybersolution.fazeal.common.dto.MessageResponse;
import com.cybersolution.fazeal.logistics.dto.JwtResponseDTO;
import com.cybersolution.fazeal.logistics.dto.LoginRequestDTO;
import com.cybersolution.fazeal.logistics.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Service
@Validated
public class AuthMutation implements GraphQLMutationResolver{
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	public JwtResponseDTO authenticateUser(@Valid LoginRequestDTO loginRequestDTO) {
		return authService.loginByUsernamePwd(loginRequestDTO.getUserName(),
				loginRequestDTO.getPassword(), httpServletRequest);
	}
	
	public MessageResponse logout(@Valid Long userId,@Valid String refreshToken) {
		String message = authService.logout(userId, refreshToken);
		return MessageResponse.builder().status(HttpStatus.OK.value()).message(message).build();
	}

}
