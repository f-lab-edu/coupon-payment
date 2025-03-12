/*
package com.couponPayment.repository;

import com.couponPayment.entity.TransactionInfoTb;
import com.couponPayment.entity.dto.TransactionInfoDto;
import com.couponPayment.entity.mapper.TransactionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class TransactionInfoRepositoryTest extends CommonReposity{
    @Autowired
    private TransactionInfoRepository transactionInfoRepository;
    @MockitoBean
    private TransactionMapper transactionMapper;

    private TransactionInfoTb transactionInfoTb;
    private TransactionInfoDto transactionInfoDto;

    @BeforeEach
    public void setting(){
        transactionInfoDto = TransactionInfoDto
                .builder()
                .tranNum("tranNum")
                .requestDt("2025-03-10T09:23:27+09:00")
                .approvalAmount(1000)
                .approvalDt("2025-03-10T09:23:27+09:00")
                .cancelAmount(1000)
                .cancelDt("2025-03-10T09:23:27+09:00")
                .installment(0)
                .callbackUrl("https://www.naver.com")
                .myWalletInfoId(null)
                .userInfoId(null)
                .storeInfoId(null)
                .walletReqId(null)
                .build();

        transactionInfoTb =new TransactionInfoTb(
                null,  // ID (자동 생성)
                null,
                null,
                null,
                null,
                transactionInfoDto.getTranNum(),
                Timestamp.valueOf(LocalDateTime.parse(transactionInfoDto.getRequestDt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)),
                transactionInfoDto.getApprovalAmount(),
                Timestamp.valueOf(LocalDateTime.parse(transactionInfoDto.getApprovalDt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)),
                transactionInfoDto.getApprovalNum(),
                transactionInfoDto.getCancelAmount(),
                Timestamp.valueOf(LocalDateTime.parse(transactionInfoDto.getCancelDt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)),
                transactionInfoDto.getInstallment(),
                transactionInfoDto.getCallbackUrl()
        );

        // Mock 설정
        when(transactionMapper.toEntity(transactionInfoDto)).thenReturn(transactionInfoTb);
        when(transactionMapper.toDto(transactionInfoTb)).thenReturn(transactionInfoDto);

        transactionInfoTb = transactionInfoRepository.save(transactionMapper.toEntity(transactionInfoDto));
        assertThat(transactionInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("myWalletInfoId","userInfoId","storeInfoId" ,"walletReqId","requestDt","approvalDt","cancelDt")
                .isEqualTo(transactionInfoTb);

    }

    @Test
    public void findById(){
        transactionInfoTb = transactionInfoRepository.findById(1L).get();
        transactionInfoDto = transactionMapper.toDto(transactionInfoTb);

        assertThat(transactionInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("myWalletInfoId","userInfoId","storeInfoId" ,"walletReqId","requestDt","approvalDt","cancelDt")
                .isEqualTo(transactionInfoTb);

        assertThat(transactionInfoTb.getApprovalDt().toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .isEqualTo("2025-03-10 09:23:27");
    }
}*/
