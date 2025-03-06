package com.couponPayment.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentServiceTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void payment(){
        String customerKey = "Y5zNpssBs1";
        String billingKey = "Hy_l357saUspeqnf2eoePY84C6p9dUGR7-q1hT8EZDo=";
    }
}
