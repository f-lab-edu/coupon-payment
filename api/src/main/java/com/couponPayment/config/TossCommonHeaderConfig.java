package com.couponPayment.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Base64;

public class TossCommonHeaderConfig {

    public static HttpHeaders tossHeader(String tossSecretKey) {

        String secretKey = tossSecretKey;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
