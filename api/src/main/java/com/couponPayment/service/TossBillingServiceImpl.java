package com.couponPayment.service;

import com.couponPayment.config.TossCommonHeaderConfig;
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
    private final ApiService<TossBillingRes> getBillingResApiService;
    @Value("${toss.paymentUrl}")
    private String paymentUrl;

    @Value("${toss.paymentCancelUrl}")
    private String paymentCancelUrl;

    @Value("${toss.billingKeyUrl}")
    private String billingKeyUrl;

    @Override
    public TossBillingRes getBillingKey(TossBillingReq tossBillingReq) {

        HttpHeaders headers = TossCommonHeaderConfig.tossHeader(tossBillingReq.getSecretKey());
        TossBillingRes tossBillingRes = getBillingResApiService.post(billingKeyUrl, headers, TossBillingRes.class).getBody();

        return tossBillingRes;
    }

    @Override
    public TossBillingPaymentRes billingPayment(TossBillingPaymentReq tossBillingPaymentReq) {
        String url = paymentUrl + tossBillingPaymentReq.getCardId();

        HttpHeaders headers = TossCommonHeaderConfig.tossHeader(tossBillingPaymentReq.getSecretKey());
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

        HttpHeaders headers = TossCommonHeaderConfig.tossHeader(tossBillingPaymentCancelReq.getSecretKey());
        /** Todo
         * 서비스 -> 외부 API(토스)
         * 성공 실패, 에러에 따른 핸들링 필요
         * */
        TossBillingPaymentCancelRes tossBillingPaymentCancelRes = billingPaymentCancelResApiService.post(url, headers, TossBillingPaymentCancelRes.class).getBody();

        return tossBillingPaymentCancelRes;
    }
}
