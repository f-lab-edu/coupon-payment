package com.couponPayment.repository;

import com.couponPayment.entity.TransactionInfoTb;
import com.couponPayment.entity.UserInfoTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfoTb, Long> {
}
