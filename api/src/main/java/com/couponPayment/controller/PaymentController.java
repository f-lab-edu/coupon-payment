package com.couponPayment.controller;

import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.PaymentCancelReq;
import com.couponPayment.dto.PaymentCancelRes;
import com.couponPayment.dto.PaymentHistoryReq;
import com.couponPayment.dto.PaymentHistoryRes;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.PaymentRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping
    public ResponseEntity<PaymentRes> payment(@RequestBody PaymentReq paymentReq){

        PaymentRes paymentRes = PaymentRes.builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .orderNum("12345")
                .cardCompany("NH")
                .cardNum("123456789")
                .cardName("농협 체크")
                .approvalAmount(1000)
                .approvalNum("A12345")
                .approvalDate("20250302112233")
                .paymentKey("txn_12345")
                .resultCode("0000")
                .resultMessage("성공")
                .build();

        return ResponseEntity.ok(paymentRes);
    }

    @PostMapping("/cancel")
    public ResponseEntity<PaymentCancelRes> paymentCancel(@RequestBody PaymentCancelReq paymentCancelReq){
        PaymentCancelRes paymentCancelRes = PaymentCancelRes
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .orderNum("12345")
                .cancelAmount(1000)
                .cancelDate("20250302112233")
                .resultCode("0000")
                .resultMessage("성공")
                .build();
        return ResponseEntity.ok(paymentCancelRes);
    }

    @PostMapping("/history")
    public ResponseEntity<PaymentHistoryRes> paymentCancel(@RequestBody PaymentHistoryReq paymentHistoryReq){
        List<PaymentHistoryRes.Payments> payments = new ArrayList<>();
        PaymentHistoryRes.Payments payments1 = PaymentHistoryRes.Payments
                .builder()
                .amount(1000)
                .paymentStatus(PaymentStatus.DONE)
                .orderNum("12345")
                .approvalDate("20250302112233")
                .build();

        PaymentHistoryRes.Payments payments2 = PaymentHistoryRes.Payments
                .builder()
                .amount(500)
                .paymentStatus(PaymentStatus.CANCEL)
                .orderNum("123456")
                .approvalDate("20250302112244")
                .build();
        payments.add(payments1);
        payments.add(payments2);

        PaymentHistoryRes paymentHistoryRes = PaymentHistoryRes
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .payments(payments)
                .build();

        return ResponseEntity.ok(paymentHistoryRes);
    }
}
