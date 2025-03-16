package com.couponPayment.repository;

import com.couponPayment.entity.MyWalletInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyWalletInfoRepository extends JpaRepository<MyWalletInfo, Long> {
}
