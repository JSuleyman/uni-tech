package com.example.unitech.security.controller;

import com.example.unitech.security.dto.request.AuthenticationRequestDTO;
import com.example.unitech.security.dto.request.RegisterRequestDTO;
import com.example.unitech.security.dto.response.AuthenticationResponse;
import com.example.unitech.security.service.AuthenticationService;
import com.example.unitech.security.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final LogoutService logoutService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody
                                                           RegisterRequestDTO request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody
                                                               AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        logoutService.logout(request, response, authentication);
    }
}
