package com.eduardoaf.balance.shared.infrastructure.formatters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public final class PasswordFormatter {

    public static PasswordFormatter getInstance() {
        return (new PasswordFormatter());
    }

    public String getHashedPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    public boolean isPasswordValid(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = getHashedPassword(rawPassword);
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
