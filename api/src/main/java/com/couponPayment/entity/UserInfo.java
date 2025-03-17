package com.couponPayment.entity;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class UserInfo extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userInfoId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeInfoId")
    private StoreInfo storeInfo;

    @Column(length = 32)
    private String name;

    /** 전화번호 */
    @Column(length = 32)
    private String phone;

    /** 메일주소 */
    private String mail;

    /** 사용 유무 (0 : 정상, 1 : 탈퇴) */
    @Column(name = "useFlag")
    private Integer useFlag;

    /** 가맹점에서 만든 Id */
    private String merchantMemberId;

    /** Toss 결제하기 위한 고객 Key */
    private String customerKey;

    @OneToMany(mappedBy = "userInfo")
    private List<MyWalletInfo> myWalletInfos = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    private List<TransactionInfo> transactionInfos = new ArrayList<>();
    protected UserInfo() {}
}
