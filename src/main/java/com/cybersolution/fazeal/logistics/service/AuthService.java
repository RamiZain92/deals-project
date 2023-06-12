package com.cybersolution.fazeal.logistics.service;


import javax.servlet.http.HttpServletRequest;

import com.cybersolution.fazeal.logistics.dto.JwtResponseDTO;

public interface AuthService {


	public JwtResponseDTO loginByUsernamePwd(String username, String password, HttpServletRequest httpServletRequest);
	public String logout(Long userId,String refreshToken);
}
