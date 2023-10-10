package com.dominiknagy.tinyexpense.TinyExpense.utility;

import java.security.SecureRandom;
import java.util.Base64;

public class SaltGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}