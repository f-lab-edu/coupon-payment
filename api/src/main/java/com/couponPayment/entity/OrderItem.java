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
public class OrderItem extends BaseEntity{

    @Id
    @Column(name = "orderItemId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer quantity; //해당 상품 주문 개수 ex)양말 * 2

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    private Order orderTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product productTb;
}
