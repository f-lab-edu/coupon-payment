package com.couponPayment.config;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.=@";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        if (length < 2 || length > 300) {
            throw new IllegalArgumentException("Length must be between 2 and 300.");
        }

        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARSET.length());
            result.append(CHARSET.charAt(index));
        }

        return result.toString();
    }
}
