package com.couponPayment.repository;

import com.couponPayment.entity.TransactionInfoTb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfoTb, Long> {
}
