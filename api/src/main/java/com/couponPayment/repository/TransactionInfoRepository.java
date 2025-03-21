package com.couponPayment.repository;

import com.couponPayment.entity.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long> {
    Optional<TransactionInfo> findByTranNumAndStatus(String tranNum, String status);
}
