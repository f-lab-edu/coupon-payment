package com.couponPayment.controller;

import com.couponPayment.consts.CommonResult;
import com.couponPayment.consts.PaymentStatus;
import com.couponPayment.dto.ApiResponse;
import com.couponPayment.dto.PaymentCancelReq;
import com.couponPayment.dto.PaymentCancelRes;
import com.couponPayment.dto.PaymentHistoryReq;
import com.couponPayment.dto.PaymentHistoryRes;
import com.couponPayment.dto.PaymentReq;
import com.couponPayment.dto.PaymentRes;
import com.couponPayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentRes>> payment(@RequestBody PaymentReq paymentReq) {

        /*PaymentRes paymentRes = PaymentRes.builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .orderNum("12345")
                .cardCompany("NH")
                .cardNum("123456789")
                .approvalAmount(1000)
                .approvalNum("A12345")
                .approvalDate("20250302112233")
                .tranNum("txn_12345")
                .resultCode("0000")
                .resultMessage("성공")
                .build();*/
        //PaymentRes paymentRes = new PaymentRes("가맹점","young","12345","NH","123",1000,"aNum","adt","tran");
        ApiResponse<PaymentRes> apiResponse = paymentService.payment(paymentReq);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/cancel")
    public ResponseEntity<ApiResponse<PaymentCancelRes>> paymentCancel(@RequestBody PaymentCancelReq paymentCancelReq) {
        /*PaymentCancelRes paymentCancelRes = PaymentCancelRes
                .builder()
                .merchantId("가맹점")
                .merchantMemberId("young")
                .orderNum("12345")
                .cancelAmount(1000)
                .cancelDate("20250302112233")
                .resultCode("0000")
                .resultMessage("성공")
                .build();*/
        logger.info(paymentCancelReq.toString());
        //PaymentCancelRes paymentCancelRes = new PaymentCancelRes("가맹점","young","12345","orderNum",1000,"20250321");
        ApiResponse<PaymentCancelRes> apiResponse = paymentService.paymentCancel(paymentCancelReq);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/history")
    public ResponseEntity<ApiResponse<PaymentHistoryRes>> paymentHistory(@RequestBody PaymentHistoryReq paymentHistoryReq) {
        /*List<PaymentHistoryRes.Payments> payments = new ArrayList<>();
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
                .build();*/

        List<PaymentHistoryRes.Payments> payments = new ArrayList<>();
        PaymentHistoryRes.Payments payments1 = new PaymentHistoryRes.Payments("12345", 1000, PaymentStatus.DONE, "20250302112233");
        PaymentHistoryRes.Payments payments2 = new PaymentHistoryRes.Payments("123456", 500, PaymentStatus.CANCEL, "20250302112244");

        payments.add(payments1);
        payments.add(payments2);

        PaymentHistoryRes paymentHistoryRes = new PaymentHistoryRes("가맹점", "young", payments);
        ApiResponse<PaymentHistoryRes> apiResponse = ApiResponse.of(CommonResult.E0000, paymentHistoryRes);
        return ResponseEntity.ok(apiResponse);
    }
}
