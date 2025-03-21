package com.couponPayment.service;

import com.couponPayment.util.TossCommonHeaderUtil;
import com.couponPayment.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TossBillingService{

    private final ApiService<TossBillingPaymentRes> billingResApiService;
    private final ApiService<TossBillingPaymentCancelRes> billingPaymentCancelResApiService;
    private final ApiService<TossBillingRes> getBillingResApiService;
    private final TossCommonHeaderUtil tossCommonHeaderUtil;

    @Value("${toss.paymentUrl}")
    private String paymentUrl;

    @Value("${toss.paymentCancelUrl}")
    private String paymentCancelUrl;

    @Value("${toss.billingKeyUrl}")
    private String billingKeyUrl;

    public TossBillingRes getBillingKey(TossBillingReq tossBillingReq) {

        HttpHeaders headers = tossCommonHeaderUtil.tossHeader(tossBillingReq.getSecretKey());
        TossBillingRes tossBillingRes = getBillingResApiService.post(billingKeyUrl, headers, TossBillingRes.class).getBody();

        return tossBillingRes;
    }

    public TossBillingPaymentRes billingPayment(TossBillingPaymentReq tossBillingPaymentReq) {
        String url = paymentUrl + tossBillingPaymentReq.getCardId();

        HttpHeaders headers = tossCommonHeaderUtil.tossHeader(tossBillingPaymentReq.getSecretKey());
        /** Todo
         * 서비스 -> 외부 API(토스)
         * 성공 실패, 에러에 따른 핸들링 필요
        * */
        TossBillingPaymentRes tossBillingPaymentRes = billingResApiService.post(url, headers, TossBillingPaymentRes.class).getBody();

        return tossBillingPaymentRes;
    }

    public TossBillingPaymentCancelRes billingPaymentCancel(TossBillingPaymentCancelReq tossBillingPaymentCancelReq) {
        String url = paymentCancelUrl.replace("{paymentKey}", tossBillingPaymentCancelReq.getPaymentKey());

        HttpHeaders headers = tossCommonHeaderUtil.tossHeader(tossBillingPaymentCancelReq.getSecretKey());
        /** Todo
         * 서비스 -> 외부 API(토스)
         * 성공 실패, 에러에 따른 핸들링 필요
         * */
        TossBillingPaymentCancelRes tossBillingPaymentCancelRes = billingPaymentCancelResApiService.post(url, headers, TossBillingPaymentCancelRes.class).getBody();

        return tossBillingPaymentCancelRes;
    }
}
