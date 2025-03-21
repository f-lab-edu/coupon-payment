package com.couponPayment.repository;

import com.couponPayment.entity.MyWalletInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyWalletInfoRepository extends JpaRepository<MyWalletInfo, Long> {
    Optional<MyWalletInfo> findByCardIdAndUseFlag(String cardId, Integer useFlag);
}
