package com.couponPayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@Entity
@Setter
@NoArgsConstructor(access = PROTECTED)
public class Coupon extends BaseEntity{
    @Id
    @Column(name = "couponId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code; // ex. SPRING5000
    private int discountAmount;// 정액 할인
    private double discountRate; // 정률 할인도 가능 (ex. 10%)

    private Integer quantity; //발급 가능한 수
    private String issuedDt; //생성 날짜
    private String expiresDt; //만료 기한

    @OneToMany(mappedBy = "coupon")
    private List<CouponIssue> issues = new ArrayList<>();
}
