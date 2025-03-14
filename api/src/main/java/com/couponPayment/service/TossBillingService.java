package com.couponPayment.service;

import com.couponPayment.dto.TossBillingPaymentCancelReq;
import com.couponPayment.dto.TossBillingPaymentCancelRes;
import com.couponPayment.dto.TossBillingPaymentReq;
import com.couponPayment.dto.TossBillingPaymentRes;
import com.couponPayment.dto.TossBillingReq;
import com.couponPayment.dto.TossBillingRes;

public interface TossBillingService {
    TossBillingRes getBillingKey(TossBillingReq tossBillingReq);
    TossBillingPaymentRes billingPayment(TossBillingPaymentReq tossBillingPaymentReq);
    TossBillingPaymentCancelRes billingPaymentCancel(TossBillingPaymentCancelReq tossBillingPaymentCancelReq);

}
