package com.couponPayment.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentCancelReq {
    private String merchantId; //가맹점 Id
    private String merchantMemberId; //회원 Id
    private String tranNum; //거래번호
}
