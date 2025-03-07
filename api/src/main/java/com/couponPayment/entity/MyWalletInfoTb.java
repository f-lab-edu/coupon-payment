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
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MyWalletInfoTb extends BaseEntity{
    @Id
    @Column(name = "myWalletInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfoTb userInfoTb;

    @Column(length = 64)
    private String cardId;

    private String cardCompany;
    private String cardNumber;
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String cardType;
    private String ownerType;

    @OneToMany(mappedBy = "myWalletInfoTb")
    private List<TransactionInfoTb> transactionInfoTb = new ArrayList<>();
}
