package com.couponPayment.entity.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
/**
 * TransactionInfo는 값이 변하는 경우가 많아
 * build 패턴을 사용하여 원하는 파라미터만 변경하여 적용 예정
 * */
public class TransactionInfoDto {
    /** 이 값으로 거래 취소 */
    private String tranNum;

    private String requestDt;
    private Integer amount;
    private Integer approvalAmount;
    private String approvalDt;
    private String approvalNum;
    private Integer cancelAmount;
    private String cancelDt;
    private Integer installment;
    private String callbackUrl;
    private String status;

    private Long myWalletInfoId;
    private Long userInfoId;
    private Long storeInfoId;
    private Long walletReqId;
}
