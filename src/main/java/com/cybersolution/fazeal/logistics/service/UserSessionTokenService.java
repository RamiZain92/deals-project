package com.cybersolution.fazeal.logistics.service;

import com.cybersolution.fazeal.logistics.entity.UserSessionTokenEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface UserSessionTokenService {

	public Optional<UserSessionTokenEntity> findByRefreshToken(String refreshToken);

	public Optional<UserSessionTokenEntity> findByIdAndActiveAndRefreshToken(Long id, String refreshToken);

	public UserSessionTokenEntity createUserSessionToken(Long userId, String accessToken,
			HttpServletRequest httpServletRequest);

	public UserSessionTokenEntity updateUserSessionToken(Long id, String accessToken, String refreshToken);

	public UserSessionTokenEntity verifyExpiration(UserSessionTokenEntity refreshTokenEntity);
}
