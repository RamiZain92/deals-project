package com.cybersolution.fazeal.logistics.service.impl;

import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.constants.RoleType;
import com.cybersolution.fazeal.logistics.constants.SignUpProcess;
import com.cybersolution.fazeal.logistics.constants.Status;
import com.cybersolution.fazeal.logistics.dto.JwtResponseDTO;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.entity.UserSessionTokenEntity;
import com.cybersolution.fazeal.logistics.repository.UserRepository;
import com.cybersolution.fazeal.logistics.repository.UserSessionTokenRepository;
import com.cybersolution.fazeal.logistics.security.jwt.JwtUtils;
import com.cybersolution.fazeal.logistics.security.services.UserDetailsImpl;
import com.cybersolution.fazeal.logistics.service.AuthService;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private Utility utility;

    @Autowired
    private Messages messages;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserSessionTokenServiceImpl userSessionTokenServiceImpl;

    @Autowired
    private UserSessionTokenRepository sessionTokenRepository;
    @Override
    public JwtResponseDTO loginByUsernamePwd(String username, String password, HttpServletRequest httpServletRequest) {

        if (!utility.isPasswordValidator(password)) {
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.PASSWORD_INVALID,
                    messages.get(AppConstants.PASSWORD_NOT_EMPTY));
        }

        if (Objects.isNull(username)) {
            throw new GenericException(HttpStatus.BAD_REQUEST, AppConstants.USERNAME_REQUIRED,
                    messages.get(AppConstants.USERNAME_REQUIRED));
        }

        UserEntity userEntity = userRepository.findByUserNameAndActive(username, Status.ACTIVE).orElse(null);
        if (Objects.isNull(userEntity)) {
            throw new GenericException(HttpStatus.NOT_FOUND, AppConstants.USER_NOT_FOUND,
                    messages.get(AppConstants.USER_NOT_FOUND));
        }

        userEntity = userRepository.findByIdAndSignUpProcess(userEntity.getId(), SignUpProcess.DONE).orElse(null);
        if (Objects.isNull(userEntity)) {
            throw new GenericException(HttpStatus.CONFLICT, AppConstants.SIGN_UP_NOT_DONE,
                    messages.get(AppConstants.SIGN_UP_NOT_DONE));
        }

        if(!userEntity.getRoleEntity().getRoleType().equals(RoleType.ROLE_USER)){
            throw new GenericException(HttpStatus.CONFLICT, AppConstants.NOT_AUTHORIZED_TO_LOGIN,
                    messages.get(AppConstants.NOT_AUTHORIZED_TO_LOGIN));
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String jwtAccessToken = jwtUtils.generateJwtToken(userDetails);

        UserSessionTokenEntity userSessionTokenEntity = userSessionTokenServiceImpl.createUserSessionToken(userDetails.getId(),
                jwtAccessToken,httpServletRequest);

        return JwtResponseDTO.builder().id(userSessionTokenEntity.getId()).userId(userDetails.getId())
                .username(userDetails.getUsername()).firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName()).email(userDetails.getEmail())
                .accessToken(userSessionTokenEntity.getJwtAccessToken())
                .refreshToken(userSessionTokenEntity.getRefreshToken()).tokenType(AppConstants.BEARER).roles(roles)
                .build();
    }

    @Override
    @Transactional
    public String logout(Long userId, String refreshToken) {
        sessionTokenRepository.removeByUserIdAndRefreshToken(userId, refreshToken);
        return AppConstants.LOGGED_OUT;
    }
}
