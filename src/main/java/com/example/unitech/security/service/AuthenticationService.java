package com.example.unitech.security.service;

import com.example.unitech.security.dto.request.AuthenticationRequestDTO;
import com.example.unitech.security.dto.request.RegisterRequestDTO;
import com.example.unitech.security.dto.response.AuthenticationResponse;
import com.example.unitech.security.entity.Token;
import com.example.unitech.security.entity.User;
import com.example.unitech.security.enums.Role;
import com.example.unitech.security.enums.TokenType;
import com.example.unitech.security.custom_exception.InvalidPinException;
import com.example.unitech.security.custom_exception.PinAlreadyExistsException;
import com.example.unitech.security.custom_exception.InvalidPasswordException;
import com.example.unitech.security.repository.TokenRepository;
import com.example.unitech.security.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository repository;
    TokenRepository tokenRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequestDTO request) {
        if (repository.findAll().stream()
                .anyMatch(user -> user.getPin().equalsIgnoreCase(request.getPin()))) {
            throw new PinAlreadyExistsException();
        }

        var user = User.builder()
                .pin(request.getPin())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        return AuthenticationResponse.builder()
                .token(null)
                .message("Successfully")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDTO request) {
        var userByPin = repository.findByPin(request.getPin())
                .orElseThrow(InvalidPinException::new);

        if (!passwordEncoder.matches(request.getPassword(), userByPin.getPassword())) {
            throw new InvalidPasswordException();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPin(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(userByPin);
        revokeAllUserTokens(userByPin);
        saveUserToken(userByPin, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    //Helper Methods
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
