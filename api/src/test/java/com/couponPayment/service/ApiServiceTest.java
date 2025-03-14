package com.couponPayment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) // 포트를 0으로 설정하면 랜덤 포트를 사용
class ApiServiceTest {
    
    /**
     * 통합 테스트로 설정한 restTemplate, apiService 동작 확인
     * */
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiService<String> apiService;

    @Test
    public void getWithoutClass(){
        stubFor(get(urlEqualTo("/api/test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello WireMock")));

        // WireMock 서버가 로컬호스트의 랜덤 포트로 실행되므로, 해당 포트를 주입받아 URL을 구성해야 합니다.
        // @LocalServerPort나 환경변수를 활용할 수 있습니다.
        String url = "http://localhost:8080/api/test"; // 실제 포트 번호로 변경 필요

        ResponseEntity<String> response = apiService.get(url, new HttpHeaders(), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello WireMock", response.getBody());
    }
}