package com.example.unitech.security.service;

import com.example.unitech.security.custom_exception.InvalidPasswordException;
import com.example.unitech.security.custom_exception.InvalidPinException;
import com.example.unitech.security.custom_exception.PinAlreadyExistsException;
import com.example.unitech.security.dto.request.AuthenticationRequestDTO;
import com.example.unitech.security.dto.request.RegisterRequestDTO;
import com.example.unitech.security.dto.response.AuthenticationResponse;
import com.example.unitech.security.entity.User;
import com.example.unitech.security.enums.Role;
import com.example.unitech.security.repository.TokenRepository;
import com.example.unitech.security.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    private RegisterRequestDTO registerRequestDTO;
    private AuthenticationRequestDTO authenticationRequestDTO;

    @BeforeEach
    public void setUp() {
        registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setPin("1234");
        registerRequestDTO.setPassword("password");

        authenticationRequestDTO = new AuthenticationRequestDTO();
        authenticationRequestDTO.setPin("1234");
        authenticationRequestDTO.setPassword("password");
    }

    @AfterEach
    public void tearDown() {
        registerRequestDTO = null;
        authenticationRequestDTO = null;
    }

    @Test
    public void testRegisterWhenNewUserThenSuccess() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        when(passwordEncoder.encode(registerRequestDTO.getPassword())).thenReturn("encodedPassword");

        AuthenticationResponse response = authenticationService.register(registerRequestDTO);

        verify(userRepository, times(1)).save(any(User.class));
        assertThat(response.getMessage()).isEqualTo("Successfully");
    }

    @Test
    public void testRegisterWhenExistingUserThenPinAlreadyExistsException() {
        User existingUser = User.builder()
                .pin(registerRequestDTO.getPin())
                .password(registerRequestDTO.getPassword())
                .role(Role.USER)
                .build();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(existingUser));

        assertThatThrownBy(() -> authenticationService.register(registerRequestDTO))
                .isInstanceOf(PinAlreadyExistsException.class);
    }

    @Test
    public void testAuthenticateWhenUserIsSuccessfullyAuthenticatedThenReturnAuthenticationResponseWithToken() {
        User existingUser = User.builder()
                .pin(authenticationRequestDTO.getPin())
                .password(authenticationRequestDTO.getPassword())
                .role(Role.USER)
                .build();
        when(userRepository.findByPin(authenticationRequestDTO.getPin())).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches(authenticationRequestDTO.getPassword(), existingUser.getPassword())).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.getPin(),
                authenticationRequestDTO.getPassword()
        ));
        when(jwtService.generateToken(any())).thenReturn("testTokenValue");

        AuthenticationResponse response = authenticationService.authenticate(authenticationRequestDTO);

        assertThat(response.getToken()).isNotNull();
        assertThat(response.getToken()).isEqualTo("testTokenValue");
    }

    @Test
    public void testAuthenticateWhenUserIsNotFoundThenThrowInvalidPinException() {
        when(userRepository.findByPin(authenticationRequestDTO.getPin())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authenticationService.authenticate(authenticationRequestDTO))
                .isInstanceOf(InvalidPinException.class);
    }

    @Test
    public void testAuthenticateWhenPasswordIsIncorrectThenThrowInvalidPasswordException() {
        User existingUser = User.builder()
                .pin(authenticationRequestDTO.getPin())
                .password("wrongPassword")
                .role(Role.USER)
                .build();
        when(userRepository.findByPin(authenticationRequestDTO.getPin())).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.matches(authenticationRequestDTO.getPassword(), existingUser.getPassword())).thenReturn(false);

        assertThatThrownBy(() -> authenticationService.authenticate(authenticationRequestDTO))
                .isInstanceOf(InvalidPasswordException.class);
    }

    @Test
    public void testAuthenticateWhenUserPinIsInvalidThenThrowInvalidPinException() {
        when(userRepository.findByPin(authenticationRequestDTO.getPin())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authenticationService.authenticate(authenticationRequestDTO))
                .isInstanceOf(InvalidPinException.class);
    }

    @Test
    public void testAuthenticateWhenUserPasswordIsInvalidThenThrowInvalidPasswordException() {
        User user = User.builder()
                .pin(authenticationRequestDTO.getPin())
                .password(passwordEncoder.encode("wrongPassword"))
                .build();
        when(userRepository.findByPin(authenticationRequestDTO.getPin())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(authenticationRequestDTO.getPassword(), user.getPassword())).thenReturn(false);

        assertThatThrownBy(() -> authenticationService.authenticate(authenticationRequestDTO))
                .isInstanceOf(InvalidPasswordException.class);
    }
}