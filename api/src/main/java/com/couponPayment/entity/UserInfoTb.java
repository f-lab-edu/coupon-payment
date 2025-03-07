package com.couponPayment.entity;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class UserInfoTb extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userInfoId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeInfoId")
    private StoreInfoTb storeInfoTb;

    @Column(name = "name")
    private String name;

    /** 전화번호 */
    @Column(name = "phone")
    private String phone;

    /** 메일주소 */
    @Column(name = "mail")
    private String mail;

    /** 사용 유무 (0 : 정상, 1 : 탈퇴) */
    @Column(name = "useFlag")
    private Integer useFlag;


    @OneToMany(mappedBy = "userInfoTb")
    private List<MyWalletInfoTb> myWalletInfoTbs = new ArrayList<>();
    protected UserInfoTb() {}
}
