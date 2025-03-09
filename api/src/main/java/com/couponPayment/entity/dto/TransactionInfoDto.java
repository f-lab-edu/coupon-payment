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

    //private String cardId;
    //private String orderNum;
   // private String orderId;
    //private Integer amount;
    private String tranNum;

    private String requestDt;

    private Integer approvalAmount;
    private String approvalDt;
    private String approvalNum;
    private Integer cancelAmount;
    private String cancelDt;
    private Integer installment;
    private String callbackUrl;

    private Long myWalletInfoId;
    private Long userInfoId;
    private Long storeInfoId;
    private Long walletReqId;
}
