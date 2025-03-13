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

@Getter
@AllArgsConstructor
@Entity
public class TransactionInfo extends BaseEntity{
    @Id
    @Column(name = "transactionInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myWalletInfoId")
    private MyWalletInfo myWalletInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walletReqId")
    private WalletReq walletReq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeInfoId")
    private StoreInfo storeInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    /*@Column(length = 128)
    private String cardId;

    @Column(length = 64)
    private String orderNum;

    @Column(length = 64)
    private String orderId;*/
    @Column(length = 64)
    private String tranNum;

    private String requestDt;
    //private Integer amount;

    private Integer approvalAmount;
    private String approvalDt;

    @Column(length = 64)
    private String approvalNum;
    private Integer cancelAmount;
    private String cancelDt;
    /** 할부 */
    private Integer installment;

    @Column(length = 128)
    private String callbackUrl;

    protected TransactionInfo() {

    }
}
