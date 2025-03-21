package com.couponPayment.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
public class PaymentCancelRes{
    private final String merchantId; //가맹점 Id
    private final String merchantMemberId; //회원 Id
    private final String tranNum; //거래번호
    private final String orderNum;
    private final int cancelAmount;
    private final String cancelDate;
}
