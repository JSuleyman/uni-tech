package com.example.unitech.security;

import org.apache.commons.lang3.RandomStringUtils;

public class VerificationCodeGenerator {
    public static String generateVerificationCode() {
        return RandomStringUtils.randomAlphabetic(6);
    }
}
