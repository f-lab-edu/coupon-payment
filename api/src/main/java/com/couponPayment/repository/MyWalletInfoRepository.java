package com.couponPayment.repository;

import com.couponPayment.entity.MyWalletInfoTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyWalletInfoRepository extends JpaRepository<MyWalletInfoTb, Long> {
}
