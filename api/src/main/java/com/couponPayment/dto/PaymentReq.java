package com.couponPayment.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentReq {
    private String merchantId;
    private String merchantMemberId;
    private String orderNum;
    private String cardId;
    private int installment;
    private int amount;
}
