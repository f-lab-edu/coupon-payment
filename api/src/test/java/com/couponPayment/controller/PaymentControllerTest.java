package com.couponPayment.controller;

import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.PaymentCancelReq;
import com.couponPayment.dto.PaymentCancelRes;
import com.couponPayment.dto.PaymentHistoryReq;
import com.couponPayment.dto.PaymentHistoryRes;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.PaymentRes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc; // MockMvc를 사용하여 실제 HTTP 요청을 보냅니다.

    @Test
    void payment() throws Exception {
        PaymentReq paymentReq = PaymentReq
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .orderNum("12345")
                .installment(0)
                .amount(1000)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentReq);
        System.out.println(json);

        MvcResult result = mockMvc.perform(post("/api/v1/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))  // JSON 데이터를 body에 포함
                .andExpect(status().isOk())  // 응답 상태 코드가 200인지 확인
                .andReturn();

        // 응답 Body 확인
        String responseBody = result.getResponse().getContentAsString();
        PaymentRes paymentRes = objectMapper.readValue(responseBody, PaymentRes.class);

        assertThat(paymentRes.getMerchantId()).isEqualTo("가맹점");
        assertThat(paymentRes.getMerchantMemberId()).isEqualTo("young");
        assertThat(paymentRes.getOrderNum()).isEqualTo("12345");
        assertThat(paymentRes.getApprovalAmount()).isEqualTo(1000);
        assertThat(paymentRes.getResultCode()).isEqualTo("0000");
    }

    @Test
    void paymentCancel() throws Exception {
        PaymentCancelReq paymentCancelReq = PaymentCancelReq
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .tranNum("txn_12345")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentCancelReq);
        System.out.println(json);

        MvcResult result = mockMvc.perform(post("/api/v1/payment/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))  // JSON 데이터를 body에 포함
                .andExpect(status().isOk())  // 응답 상태 코드가 200인지 확인
                .andReturn();

        // 응답 Body 확인
        String responseBody = result.getResponse().getContentAsString();
        PaymentCancelRes paymentCancelRes = objectMapper.readValue(responseBody, PaymentCancelRes.class);

        assertThat(paymentCancelRes.getMerchantId()).isEqualTo("가맹점");
        assertThat(paymentCancelRes.getMerchantMemberId()).isEqualTo("young");
        assertThat(paymentCancelRes.getOrderNum()).isEqualTo("12345");
        assertThat(paymentCancelRes.getCancelAmount()).isEqualTo(1000);
        assertThat(paymentCancelRes.getResultCode()).isEqualTo("0000");
    }

    @Test
    void testPaymentCancel() throws Exception{
        PaymentHistoryReq paymentHistoryReq = PaymentHistoryReq
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paymentHistoryReq);
        System.out.println(json);

        MvcResult result = mockMvc.perform(post("/api/v1/payment/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))  // JSON 데이터를 body에 포함
                .andExpect(status().isOk())  // 응답 상태 코드가 200인지 확인
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        PaymentHistoryRes paymentHistoryRes = objectMapper.readValue(responseBody, PaymentHistoryRes.class);

        // 응답 Body 확인
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