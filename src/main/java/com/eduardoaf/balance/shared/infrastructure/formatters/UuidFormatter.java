package com.eduardoaf.balance.shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public final class UuidFormatter {

    public static UuidFormatter getInstance() {
        return new UuidFormatter();
    }

    public String getUuid() {
        UUID uuid = UUID.randomUUID();
        return getSha256(uuid.toString());
    }

    public String getUuid(String prefix) {
        return prefix + getUuid();
    }

    public String getUuid(String prefix, int length) {
        var sha256 = getUuid(prefix);
        return sha256.substring(0, length).toUpperCase();
    }

    private String getSha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(value.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
