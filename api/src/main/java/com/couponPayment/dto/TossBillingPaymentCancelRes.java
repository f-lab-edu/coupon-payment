package com.couponPayment.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
public class TossBillingPaymentCancelRes extends TossBillingPaymentRes{
    private final List<Cancels> cancels;

    @AllArgsConstructor
    @NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
    @Getter
    public static class Cancels {
        private final String transactionKey;
        private final String cancelReason;
        private final int taxExemptionAmount;
        private final String canceledAt;
        private final int transferDiscountAmount;
        private final int easyPayDiscountAmount;
        private final String receiptKey;
        private final String cancelStatus;
        private final String cancelRequestId;
        private final int cancelAmount;
        private final int taxFreeAmount;
        private final int refundableAmount;
    }
}
