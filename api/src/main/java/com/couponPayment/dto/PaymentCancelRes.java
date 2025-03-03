package com.couponPayment.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentCancelRes {
    private String merchantId; //가맹점 Id
    private String merchantMemberId; //회원 Id
    private String paymentKey; //거래번호
    private String orderNum;
    private int cancelAmount;
    private String cancelDate;
    private String resultCode;
    private String resultMessage;
}
