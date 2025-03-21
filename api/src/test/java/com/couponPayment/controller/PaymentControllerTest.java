package com.couponPayment.controller;

import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.ApiResponse;
import com.couponPayment.dto.PaymentCancelReq;
import com.couponPayment.dto.PaymentCancelRes;
import com.couponPayment.dto.PaymentHistoryReq;
import com.couponPayment.dto.PaymentHistoryRes;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.PaymentRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {
    /*@Autowired
    private MockMvc mockMvc; // MockMvc를 사용하여 실제 HTTP 요청을 보냅니다.

*/
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void payment() throws Exception {
        /*PaymentReq paymentReq = PaymentReq
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .orderNum("12345")
                .installment(0)
                .amount(1000)
                .build();*/

        // given
        PaymentReq paymentReq = new PaymentReq("가맹점", "young", "12345", "orderId", "cardId", 0, 1000);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentReq> requestEntity = new HttpEntity<>(paymentReq, headers);

        // when
        ResponseEntity<ApiResponse<PaymentRes>> responseEntity = restTemplate.exchange(
                "/api/v1/payment",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ApiResponse<PaymentRes>>() {}
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        ApiResponse<PaymentRes> response = responseEntity.getBody();
        assertThat(response).isNotNull();

        PaymentRes paymentRes = response.getData();

        assertThat(paymentRes.getMerchantId()).isEqualTo("가맹점");
        assertThat(paymentRes.getMerchantMemberId()).isEqualTo("young");
        assertThat(paymentRes.getOrderNum()).isEqualTo("12345");
        assertThat(paymentRes.getApprovalAmount()).isEqualTo(1000);
        assertThat(response.getResultCode()).isEqualTo("0000");
    }

    @Test
    void paymentCancel() throws Exception {
        /*PaymentCancelReq paymentCancelReq = PaymentCancelReq
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .tranNum("txn_12345")
                .build();*/

        PaymentCancelReq paymentCancelReq = new PaymentCancelReq("가맹점", "young", "txn_12345");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentCancelReq);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentCancelReq> requestEntity = new HttpEntity<>(paymentCancelReq, headers);

        // when
        ResponseEntity<ApiResponse<PaymentCancelRes>> responseEntity = restTemplate.exchange(
                "/api/v1/payment/cancel",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ApiResponse<PaymentCancelRes>>() {}
        );

        ApiResponse<PaymentCancelRes> response = responseEntity.getBody();
        PaymentCancelRes paymentCancelRes = response.getData();
        assertThat(paymentCancelRes.getMerchantId()).isEqualTo("가맹점");
        assertThat(paymentCancelRes.getMerchantMemberId()).isEqualTo("young");
        assertThat(paymentCancelRes.getOrderNum()).isEqualTo("orderNum");
        assertThat(paymentCancelRes.getCancelAmount()).isEqualTo(1000);
        assertThat(response.getResultCode()).isEqualTo("0000");
    }

    @Test
    void testHistory() throws Exception{
        /*PaymentHistoryReq paymentHistoryReq = PaymentHistoryReq
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .build();*/
        PaymentHistoryReq paymentHistoryReq = new PaymentHistoryReq("가맹점", "young");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentHistoryReq);
        System.out.println(json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentHistoryReq> requestEntity = new HttpEntity<>(paymentHistoryReq, headers);

        // when
        ResponseEntity<ApiResponse<PaymentHistoryRes>> responseEntity = restTemplate.exchange(
                "/api/v1/payment/history",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ApiResponse<PaymentHistoryRes>>() {}
        );
        ApiResponse<PaymentHistoryRes> response = responseEntity.getBody();
        PaymentHistoryRes paymentHistoryRes = response.getData();

        assertThat(paymentHistoryRes.getMerchantId()).isEqualTo("가맹점");
        assertThat(paymentHistoryRes.getMerchantMemberId()).isEqualTo("young");

        assertThat(paymentHistoryRes.getPayments().get(0).getAmount()).isEqualTo(1000);
        assertThat(paymentHistoryRes.getPayments().get(0).getPaymentStatus()).isEqualTo(PaymentStatus.DONE);
        assertThat(paymentHistoryRes.getPayments().get(0).getOrderNum()).isEqualTo("12345");
        assertThat(paymentHistoryRes.getPayments().get(0).getApprovalDate()).isEqualTo("20250302112233");

        assertThat(paymentHistoryRes.getPayments().get(1).getAmount()).isEqualTo(500);
        assertThat(paymentHistoryRes.getPayments().get(1).getPaymentStatus()).isEqualTo(PaymentStatus.CANCEL);
        assertThat(paymentHistoryRes.getPayments().get(1).getOrderNum()).isEqualTo("123456");
        assertThat(paymentHistoryRes.getPayments().get(1).getApprovalDate()).isEqualTo("20250302112244");

    }
}