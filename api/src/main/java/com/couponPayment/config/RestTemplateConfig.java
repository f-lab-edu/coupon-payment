package com.couponPayment.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        // 연결 및 소켓 타임아웃 설정
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofSeconds(5)) // 연결 타임아웃 5초
                .setResponseTimeout(Timeout.ofSeconds(10)) // 응답 타임아웃 10초
                .build();

        // 커넥션 풀 설정
        PoolingHttpClientConnectionManager poolingConnManager = new PoolingHttpClientConnectionManager();
        poolingConnManager.setMaxTotal(50); // 전체 최대 연결 수
        poolingConnManager.setDefaultMaxPerRoute(20); // 호스트당 최대 연결 수

        // HttpClient 생성
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolingConnManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // HttpComponentsClientHttpRequestFactory에 HttpClient 설정
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }
}
