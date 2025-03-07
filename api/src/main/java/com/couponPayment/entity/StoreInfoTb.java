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

@Entity
@Getter
@AllArgsConstructor
public class StoreInfoTb extends BaseEntity{
    /**매장정보 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeInfoId")
    private Long id;

    /** 가맹점 아이디 */
    @Column(name = "merchantId")
    private String merchantId;

    /** tossPayment ID*/
    private String tossPaymentId;

    @OneToMany(mappedBy = "storeInfoTb")
    private List<UserInfoTb> userInfoTbs = new ArrayList<>();

    protected StoreInfoTb() {

    }
}
