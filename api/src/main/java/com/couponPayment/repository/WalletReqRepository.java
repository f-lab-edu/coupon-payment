package com.couponPayment.repository;

import com.couponPayment.entity.WalletReqTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletReqRepository extends JpaRepository<WalletReqTb, Long> {
}
