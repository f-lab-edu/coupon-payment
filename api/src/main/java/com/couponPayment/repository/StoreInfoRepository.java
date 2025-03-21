package com.couponPayment.repository;

import com.couponPayment.entity.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreInfoRepository extends JpaRepository<StoreInfo, Long> {
    Optional<StoreInfo> findByMerchantId(String merchantId);
}
