package com.couponPayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class WalletReq extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "walletReqId")
    private Long id;

    @OneToMany(mappedBy = "walletReq")
    private List<TransactionInfo> transactionInfo = new ArrayList<>();

    /** 매장 아이디 */
    private String merchantId;

    /** 매장 멤버 아이디 */
    private String merchantMemberId;

    private String orderId;
    /** 주문번호 */
    private String orderNum;

    /** 가격 */
    private Integer amount;

    protected WalletReq() {

    }
}
