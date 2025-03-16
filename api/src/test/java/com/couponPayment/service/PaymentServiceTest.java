package com.couponPayment.service;

import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.TossBillingPaymentReq;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @MockitoBean
    private TestRestTemplate testRestTemplate;


    @Test
    public void payment(){
        PaymentReq paymentReq = PaymentReq
                .builder()
                .merchantId("merchantId")
                .merchantMemberId("young")
                .orderNum("orderNum")
                .installment(0)
                .amount(2000)
                .build();

        String customerKey = "Y5zNpssBs1";
        TossBillingPaymentReq tossBillingPaymentReq = TossBillingPaymentReq
                .builder()
                .customerKey(customerKey)
                .amount(1000)
                .orderId("orderId10")
                .orderName("orderName")
                .build();
    }
}
