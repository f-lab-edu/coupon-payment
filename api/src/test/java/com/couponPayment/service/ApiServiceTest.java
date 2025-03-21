package com.couponPayment.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) // 포트를 0으로 설정하면 랜덤 포트를 사용
class ApiServiceTest {
    
    /**
     * 통합 테스트로 설정한 restTemplate, apiService 동작 확인
     *
     * 애플리케이션 서버:
     * @SpringBootTest(webEnvironment = RANDOM_PORT)로 띄워진 실제 웹 서버는 클라이언트 요청을 처리하고, 내부 비즈니스 로직을 실행합니다.
     *
     * WireMock 서버:
     * 외부 API 호출을 모방하기 위해 별도의 WireMock 서버가 실행되고, 여기에 스텁(stub)을 등록하여 원하는 응답을 반환합니다.
     *
     * */

    @Autowired
    private ApiService<String> apiService;

    @Value("${wiremock.server.port}")
    private int wiremockPort;

    @Test
    public void getWithoutClass(){
        stubFor(get(urlEqualTo("/api/test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("Hello WireMock")));

        // WireMock 서버가 로컬호스트의 랜덤 포트로 실행되므로, 해당 포트를 주입받아 URL을 구성해야 합니다.
        // @LocalServerPort나 환경변수를 활용할 수 있습니다.
        String url = "http://localhost:"+wiremockPort+ "/api/test"; // 실제 포트 번호로 변경 필요

        ResponseEntity<String> response = apiService.get(url, new HttpHeaders(), String.class);
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat("Hello WireMock").isEqualTo(response.getBody());
    }
}