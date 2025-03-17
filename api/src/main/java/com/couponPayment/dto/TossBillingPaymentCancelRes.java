package com.couponPayment.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@SuperBuilder
public class TossBillingPaymentCancelRes extends TossBillingPaymentRes{
    private List<Cancels> cancels;

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Setter
    @Getter
    @Builder
    public static class Cancels {
        private String transactionKey;
        private String cancelReason;
        private int taxExemptionAmount;
        private String canceledAt;
        private int transferDiscountAmount;
        private int easyPayDiscountAmount;
        private String receiptKey;
        private String cancelStatus;
        private String cancelRequestId;
        private int cancelAmount;
        private int taxFreeAmount;
        private int refundableAmount;
    }
}
