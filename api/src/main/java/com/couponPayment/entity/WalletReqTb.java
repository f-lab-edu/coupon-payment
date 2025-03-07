package com.couponPayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class WalletReqTb extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walletReqId")
    private Long walletReqId;

    @OneToMany(mappedBy = "walletReqTb")
    private List<TransactionInfoTb> transactionInfoTb = new ArrayList<>();

    /** 매장 아이디 */
    @Column(name = "merchantId")
    private String merchantId;

    /** 매장 멤버 아이디 */
    @Column(name = "merchantMemberId")
    private String merchantMemberId;

    @Column(name = "orderId")
    private String orderId;
    /** 주문번호 */
    @Column(name = "orderNum")
    private String orderNum;

    /** 가격 */
    @Column(name = "amount")
    private String amount;

}
