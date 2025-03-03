package com.couponPayment.dto;

import com.couponPayment.consts.PaymentStatus;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentHistoryRes {
    private String merchantId;
    private String merchantMemberId;
    private List<Payments> payments;

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Builder
    public static class Payments{
        private String orderNum;
        private int amount;
        private PaymentStatus paymentStatus;
        private String approvalDate;
    }
}
