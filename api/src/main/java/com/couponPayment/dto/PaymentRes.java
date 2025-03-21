package com.couponPayment.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor(force = true) //force = true를 사용하여 final 필드에 null 값 허용
@Getter
public class PaymentRes {
    private final String merchantId;          // 가맹점
    private final String merchantMemberId;    // 가맹점 회원
    private final String orderNum;            // 주문번호
    private final String cardCompany;         // 결제한 카드 회사
    private final String cardNum;             // 결제한 카드 번호
    private final int approvalAmount;      // 승인 금액
    private final String approvalNum;         // 승인 번호
    private final String approvalDate; // 승인 날짜
    private final String tranNum;          // 거래 번호
    /*private String resultCode;          // 결과 값
    private String resultMessage;       // 결과 메세지*/
}
