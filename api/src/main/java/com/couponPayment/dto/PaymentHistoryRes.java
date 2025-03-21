package com.couponPayment.dto;

import com.couponPayment.consts.PaymentStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
public class PaymentHistoryRes {
    private final String merchantId;
    private final String merchantMemberId;
    private final List<Payments> payments;

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
    @Getter
    public static class Payments{
        private final String orderNum;
        private final int amount;
        private final PaymentStatus paymentStatus;
        private final String approvalDate;
    }
}
