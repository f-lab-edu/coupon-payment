package com.couponPayment.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
public class PaymentReq {
    private final String merchantId;
    private final String merchantMemberId;
    private final String orderNum;
    //중복은 안됨
    private final String orderId;
    private final String cardId;
    private final int installment;
    private final int amount;
}
