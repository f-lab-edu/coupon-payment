package com.couponPayment.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class JasyptConfigTest {

    @Test
    void stringEncryptor() {
        String password = "password1234";
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("young");
        jasypt.setAlgorithm("PBEWITHMD5ANDDES");

        //when
        String clientKey = jasypt.encrypt("test_ck_5OWRapdA8dW9EPOLkLRAro1zEqZK");
        String secretKey = jasypt.encrypt("test_sk_ex6BJGQOVDGWDEoeGGbR3W4w2zNb");
        String securityKey = jasypt.encrypt("eb419e372773b0eddd1616a3ddf0138f3e01f4cc5b5dc7d3d4b06c9a59f3e849");

        System.out.println(clientKey);
        System.out.println(secretKey);
        System.out.println(securityKey);
    }

}