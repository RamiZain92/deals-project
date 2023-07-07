package com.cybersolution.logistics.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import com.cybersolution.fazeal.logistics.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.cybersolution.fazeal.common.album.feign.AlbumApiClient;
import com.cybersolution.fazeal.common.exception.GenericException;
import com.cybersolution.fazeal.common.logistics.dto.UpdateContactNumberDTO;
import com.cybersolution.fazeal.common.logistics.dto.UpdatePasswordDTO;
import com.cybersolution.fazeal.logistics.constants.AppConstants;
import com.cybersolution.fazeal.logistics.entity.UserEntity;
import com.cybersolution.fazeal.logistics.repository.UserRepository;
import com.cybersolution.fazeal.logistics.repository.VehicleRepository;
import com.cybersolution.fazeal.logistics.response.UserResponse;
import com.cybersolution.fazeal.logistics.security.services.UserDetailsImpl;
import com.cybersolution.fazeal.logistics.service.mapper.UserMapper;
import com.cybersolution.fazeal.logistics.util.Messages;
import com.cybersolution.fazeal.logistics.util.Utility;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Messages messages;
    @Mock
    private Utility utility;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void testUpdateContactNumberWithNullCountryCode() {
        UpdateContactNumberDTO updateContactNumberDTO = new UpdateContactNumberDTO();
        updateContactNumberDTO.setContactNumber("1234567890");
        assertThrows(NullPointerException.class, () -> userServiceImpl.updateContactNumber(updateContactNumberDTO));
    }
    @Test
    void testUpdatePasswordWithNullPasswords() {
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
        updatePasswordDTO.setOldPassword("oldPassword");
        assertThrows(GenericException.class, () -> userServiceImpl.updatePassword(updatePasswordDTO));
    }
    @Test
    void testUpdatePasswordWithIncorrectConfirmPassword() {
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
        updatePasswordDTO.setOldPassword("oldPassword");
        updatePasswordDTO.setNewPassword("newPassword");
        updatePasswordDTO.setConfirmPassword("newConfirmPassword");
        when(messages.get(AppConstants.ERROR_INCORRECT_CONFIRM_PWD)).thenReturn("Incorrect confirm password");
        assertThrows(GenericException.class, () -> userServiceImpl.updatePassword(updatePasswordDTO));
    }
    @Test
    void testUpdatePasswordWithMatchingOldAndNewPassword() {
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
        updatePasswordDTO.setOldPassword("oldPassword");
        updatePasswordDTO.setNewPassword("oldPassword");
        updatePasswordDTO.setConfirmPassword("oldPassword");
        when(passwordEncoder.matches("oldPassword", "oldPasswordHashed")).thenReturn(true);
        when(passwordEncoder.encode("oldPassword")).thenReturn("oldPasswordHashed");
        when(messages.get(AppConstants.ERROR_OLD_AND_NEW_PASSWORD_ARE_MATCHING)).thenReturn("Old and new password are matching");
        assertThrows(GenericException.class, () -> userServiceImpl.updatePassword(updatePasswordDTO));
    }
    @Test
    void testUpdatePasswordWithIncorrectOldPassword() {
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
        updatePasswordDTO.setOldPassword("oldPassword");
        updatePasswordDTO.setNewPassword("newPassword");
        updatePasswordDTO.setConfirmPassword("newPassword");
        when(passwordEncoder.matches("oldPassword", "oldPasswordHashed")).thenReturn(false);
        when(messages.get(AppConstants.ERROR_INCORRECT_OLD_PWD)).thenReturn("Incorrect old password");
        assertThrows(GenericException.class, () -> userServiceImpl.updatePassword(updatePasswordDTO));
    }
    @Test
    void testUpdateEmailWithNullEmail() {
        assertThrows(GenericException.class, () -> userServiceImpl.updateEmail(null));
    }
    @Test
    void testUpdateEmailWithInvalidEmailFormat() {
        String email = "invalidEmail";
        when(utility.isEmailValid(email)).thenReturn(false);
        assertThrows(GenericException.class, () -> userServiceImpl.updateEmail(email));
    }


    @Test
    void testUpdateEmailWithExistingEmail() {
        String email = "existingEmail@example.com";
        UserEntity userEntity = new UserEntity();
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(userEntity));
        when(messages.get(AppConstants.EMAIL_ALREADY_EXIST)).thenReturn("Email already exists");
        assertThrows(GenericException.class, () -> userServiceImpl.updateEmail(email));
    }
}
 