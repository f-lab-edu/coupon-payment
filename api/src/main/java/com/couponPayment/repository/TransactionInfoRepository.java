package com.couponPayment.repository;

import com.couponPayment.entity.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long> {
}
