package com.couponPayment.entity.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionInfoDto {

    private String cardId;
    private String orderNum;
    private String orderId;
    private String tranNum;

    private Timestamp requestDt;
    private Integer amount;
    private Integer approvalAmount;
    private Timestamp approvalDt;
    private String approvalNum;
    private Integer cancelAmount;
    private Timestamp cancelDt;
    private Integer installment;
    private String callbackUrl;

    private Long myWalletInfoId;
    private Long walletReqId;
}
