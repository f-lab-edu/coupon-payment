package com.couponPayment.repository;

import com.couponPayment.entity.WalletReq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletReqRepository extends JpaRepository<WalletReq, Long> {
}
