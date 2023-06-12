package com.cybersolution.fazeal.logistics.service.impl;

import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.constants.Status;
import com.cybersolution.fazeal.logistics.entity.UserSessionTokenEntity;
import com.cybersolution.fazeal.logistics.exception.TokenRefreshException;
import com.cybersolution.fazeal.logistics.repository.UserRepository;
import com.cybersolution.fazeal.logistics.repository.UserSessionTokenRepository;
import com.cybersolution.fazeal.logistics.service.UserSessionTokenService;
import com.cybersolution.fazeal.logistics.util.DateUtill;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserSessionTokenServiceImpl implements UserSessionTokenService {

	@Value("${ciui.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private UserSessionTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	Messages messages;

	public Optional<UserSessionTokenEntity> findByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken);
	}
	
	public Optional<UserSessionTokenEntity> findByIdAndActive(Long id){
		return refreshTokenRepository.findByIdAndActive(id, Status.ACTIVE);
	}

	public Optional<UserSessionTokenEntity> findByIdAndActiveAndRefreshToken(Long id, String refreshToken) {
		return refreshTokenRepository.findByIdAndActiveAndRefreshToken(id, Status.ACTIVE, refreshToken);
	}

	public UserSessionTokenEntity createUserSessionToken(Long userId,
			String accessToken, HttpServletRequest httpServletRequest) {

		UserSessionTokenEntity userSessionTokenEntity = UserSessionTokenEntity.builder()
				.userEntity(userRepository.findById(userId).orElse(null))
				.refreshToken(UUID.randomUUID().toString()).jwtAccessToken(accessToken)
				.expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
				.browserType(Utility.getBrowserType(httpServletRequest))
				.deviceType(Utility.getDeviceType(httpServletRequest))
				.ipAddress(Utility.getIpAddress(httpServletRequest)).id(0L).build();

		userSessionTokenEntity.setActive(Status.ACTIVE);
		userSessionTokenEntity.setCreatedDate(LocalDateTime.now());

		return refreshTokenRepository.save(userSessionTokenEntity);
	}

	public UserSessionTokenEntity updateUserSessionToken(Long id, String accessToken, String refreshToken) {

		UserSessionTokenEntity userSessionTokenEntity = refreshTokenRepository.findById(id).orElse(null);
		userSessionTokenEntity.setJwtAccessToken(accessToken);
		userSessionTokenEntity.setRefreshToken(refreshToken);
		userSessionTokenEntity.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		userSessionTokenEntity.setUpdatedDate(LocalDateTime.now());
		return refreshTokenRepository.save(userSessionTokenEntity);
	}

	public UserSessionTokenEntity verifyExpiration(UserSessionTokenEntity refreshTokenEntity) {
		if (refreshTokenEntity.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenEntity);
			throw new TokenRefreshException(refreshTokenEntity.getRefreshToken(),
					AppConstants.REFRESH_TOKEN_EXPIRED_MSG);
		}

		return refreshTokenEntity;
	}
}