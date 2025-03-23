package com.couponPayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@Entity
@Setter
@NoArgsConstructor(access = PROTECTED)
public class CouponIssue extends BaseEntity{
    @Id
    @Column(name = "couponIssueId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean used; //사용 여부
    private String usedDt; //사용 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponId", nullable = false)
    private Coupon coupon;

    @OneToOne(mappedBy = "couponIssue")
    private Order order; // 쿠폰 사용 시 연결된 주문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

}
