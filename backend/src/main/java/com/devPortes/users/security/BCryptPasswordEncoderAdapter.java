package com.devPortes.users.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter {
    private final PasswordEncoder  encoder;

    public String encodePassword(String Password) {
        return encoder.encode(Password);
    }

    public boolean matchesPasswords(String passwordPlane, String saveHashPassword) {
        return encoder.matches(passwordPlane, saveHashPassword);
    }
}
