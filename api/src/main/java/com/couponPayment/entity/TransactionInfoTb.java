package com.couponPayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@Entity
public class TransactionInfoTb extends BaseEntity{
    @Id
    @Column(name = "transactionInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myWalletInfoId")
    private MyWalletInfoTb myWalletInfoTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walletReqId")
    private WalletReqTb walletReqTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeInfoId")
    private StoreInfoTb storeInfoTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfoTb userInfoTb;

    /*@Column(length = 128)
    private String cardId;

    @Column(length = 64)
    private String orderNum;

    @Column(length = 64)
    private String orderId;*/
    @Column(length = 64)
    private String tranNum;

    private Timestamp requestDt;
    //private Integer amount;
    private Integer approvalAmount;
    private Timestamp approvalDt;

    @Column(length = 64)
    private String approvalNum;
    private Integer cancelAmount;
    private Timestamp cancelDt;
    private Integer installment;

    @Column(length = 128)
    private String callbackUrl;

    protected TransactionInfoTb() {

    }
}
