package com.couponPayment.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString
public class PaymentRes extends ApiResponse{
    private String merchantId;          // 가맹점
    private String merchantMemberId;    // 가맹점 회원
    private String orderNum;            // 주문번호
    private String cardCompany;         // 결제한 카드 회사
    private String cardNum;             // 결제한 카드 번호
    private int approvalAmount;      // 승인 금액
    private String approvalNum;         // 승인 번호
    private String approvalDate; // 승인 날짜
    private String tranNum;          // 거래 번호
    /*private String resultCode;          // 결과 값
    private String resultMessage;       // 결과 메세지*/
}
