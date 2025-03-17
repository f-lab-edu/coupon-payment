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
    private final ApiService<TossBillingPaymentCancelRes> billingPaymentCancelResApiService;

    @Value("${toss.paymentUrl}")
    private String paymentUrl;

    @Value("${toss.paymentCancelUrl}")
    private String paymentCancelUrl;

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
        String url = paymentCancelUrl.replace("{paymentKey}", tossBillingPaymentCancelReq.getPaymentKey());
        String secretKey = tossBillingPaymentCancelReq.getSecretKey();

        String authHeader = "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        /** Todo
         * 서비스 -> 외부 API(토스)
         * 성공 실패, 에러에 따른 핸들링 필요
         * */
        TossBillingPaymentCancelRes tossBillingPaymentCancelRes = billingPaymentCancelResApiService.post(url, headers, TossBillingPaymentCancelRes.class).getBody();

        return tossBillingPaymentCancelRes;
    }
}
