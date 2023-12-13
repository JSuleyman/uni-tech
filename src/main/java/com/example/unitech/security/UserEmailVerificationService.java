package com.example.unitech.security;

public interface UserEmailVerificationService {
    void save(UserEmailVerification userEmailVerification);

    String verifyUser(String email, String verificationCode);

    void repeatSendVerificationCode(String email);
}
