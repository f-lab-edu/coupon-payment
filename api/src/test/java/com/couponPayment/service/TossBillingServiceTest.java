package com.couponPayment.service;

import com.couponPayment.dto.TossBillingReq;
import com.couponPayment.dto.TossBillingRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TossBillingServiceTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${toss.secretKey}")
    private String secretTossKey;

    @Test
    public void getBillingKey(){
        //http://localhost:8081/api/v1/toss/billing/success?customerKey=xFl5CrMdIL&authKey=bln_p6XnzaNmdEB
        //sucess요청이 올때마다 값이 달라지는데 어떻게 테스트 코드를 짜야할까?....
        TossBillingReq tossBillingReq = TossBillingReq
                .builder()
                .customerKey("Y5zNpssBs1")
                .authKey("bln_5KnkWkREXW1")
                .build();

        // Authorization에 사용될 시크릿 키
        String secretKey = secretTossKey;

        // Basic 인증 헤더 생성 (secretKey: 뒤에 :을 붙여서 base64로 인코딩)
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        // HttpEntity를 통해 요청 본문과 헤더를 설정
        HttpEntity<TossBillingReq> entity = new HttpEntity<>(tossBillingReq, headers);

        ResponseEntity<TossBillingRes> tossBillingRes =
                testRestTemplate.exchange("https://api.tosspayments.com/v1/billing/authorizations/issue",
                    HttpMethod.POST,
                    entity,
                        TossBillingRes.class
                );

        System.out.println(tossBillingRes.getBody());
        //TossBillingRes(mId=null, customerKey=Y5zNpssBs1, authenticatedAt=2025-03-06T16:58:17+09:00, method=카드, billingKey=Hy_l357saUspeqnf2eoePY84C6p9dUGR7-q1hT8EZDo=,
        // card=TossBillingRes.CardInfo(issuerCode=91, acquirerCode=91, number=94411702****274*, cardType=체크, ownerType=개인),
        // cardCompany=농협, cardNumber=94411702****274*)
    }
}