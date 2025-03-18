package com.couponPayment.service;

import com.couponPayment.dto.PaymentCancelReq;
import com.couponPayment.dto.PaymentCancelRes;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.PaymentRes;

public interface PaymentService {
    PaymentRes payment(PaymentReq paymentReq);
    PaymentCancelRes paymentCancel(PaymentCancelReq paymentCancelReq);
}
