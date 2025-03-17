package com.couponPayment.service;

import com.couponPayment.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class TossBillingServiceImpl implements TossBillingService{

    private final ApiService<TossBillingPaymentRes> billingResApiService;

    @Value("${toss.paymentUrl}")
    private String paymentUrl;

    @Override
    public TossBillingRes getBillingKey(TossBillingReq tossBillingReq) {
        return null;
    }

    @Override
    public TossBillingPaymentRes billingPayment(TossBillingPaymentReq tossBillingPaymentReq) {
        String url = paymentUrl + tossBillingPaymentReq.getCardId();
        String secretKey = tossBillingPaymentReq.getSecretKey();

        String authHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        /** Todo
         * 서비스 -> 외부 API(토스)
         * 성공 실패, 에러에 따른 핸들링 필요 
        * */
        TossBillingPaymentRes tossBillingPaymentRes = billingResApiService.post(url, headers, TossBillingPaymentRes.class).getBody();

        return tossBillingPaymentRes;
    }

    @Override
    public TossBillingPaymentCancelRes billingPaymentCancel(TossBillingPaymentCancelReq tossBillingPaymentCancelReq) {
        return null;
    }
}
