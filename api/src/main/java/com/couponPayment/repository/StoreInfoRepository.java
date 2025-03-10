package com.couponPayment.repository;

import com.couponPayment.entity.StoreInfoTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreInfoRepository extends JpaRepository<StoreInfoTb, Long> {
}
