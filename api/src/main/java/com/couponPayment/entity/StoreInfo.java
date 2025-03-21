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
public class StoreInfo extends BaseEntity{
    /**매장정보 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeInfoId")
    private Long id;

    /** 가맹점 아이디 */
    @Column(length = 32)
    private String merchantId;

    /** tossPayment ID secret Key*/
    @Column(length = 32)
    private String tossPaymentId;

    @OneToMany(mappedBy = "storeInfo")
    private List<UserInfo> userInfos = new ArrayList<>();
    @OneToMany(mappedBy = "storeInfo")
    private List<TransactionInfo> transactionInfos = new ArrayList<>();
    protected StoreInfo() {

    }
}
