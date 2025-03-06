package com.couponPayment.service;

import com.couponPayment.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentServiceTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${toss.secretKey}")
    private String secretTossKey;
    @Value("${toss.payment_url}")
    private String payment_url;
    @Test
    public void payment(){
        String customerKey = "Y5zNpssBs1";
        String billingKey = "Hy_l357saUspeqnf2eoePY84C6p9dUGR7-q1hT8EZDo=";
        TossBillingPaymentReq tossBillingPaymentReq = TossBillingPaymentReq
                .builder()
                .customerKey(customerKey)
                .amount(1000)
                .orderId("orderId2")
                .orderName("orderName")
                .build();
        //
        String url = payment_url+billingKey;

        // Authorization에 사용될 시크릿 키
        String secretKey = secretTossKey;

        // Basic 인증 헤더 생성 (secretKey: 뒤에 :을 붙여서 base64로 인코딩)
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
        System.out.println(authHeader);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // HttpEntity를 통해 요청 본문과 헤더를 설정
        HttpEntity<TossBillingPaymentReq> entity = new HttpEntity<>(tossBillingPaymentReq, headers);

        ResponseEntity<TossBillingPaymentRes> tossBillingPaymentResResponse =
                testRestTemplate.exchange(url,
                        HttpMethod.POST,
                        entity,
                        TossBillingPaymentRes.class
                );
        System.out.println(tossBillingPaymentResResponse.getBody());
    }
}
