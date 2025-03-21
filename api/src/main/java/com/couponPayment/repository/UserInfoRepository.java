package com.couponPayment.repository;

import com.couponPayment.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByMerchantMemberIdAndStoreInfo_MerchantId(String merchantMemberId, String merchantId);
}
