package com.couponPayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity
public class MyWalletInfo extends BaseEntity{
    @Id
    @Column(name = "myWalletInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    @Column(length = 64)
    private String cardId;

    @Column(length = 32)
    private String cardCompany;
    @Column(length = 32)
    private String cardNumber;
    /** 카드 발급사 */
    @Column(length = 4)
    private String issuerCode;
    @Column(length = 4)
    /** 카드 발급사 */
    private String acquirerCode;
    @Column(length = 32)
    private String number;
    @Column(length = 32)
    private String cardType;
    @Column(length = 32)
    private String ownerType;

    @OneToMany(mappedBy = "myWalletInfo")
    private List<TransactionInfo> transactionInfo = new ArrayList<>();
    protected MyWalletInfo() {}
}
