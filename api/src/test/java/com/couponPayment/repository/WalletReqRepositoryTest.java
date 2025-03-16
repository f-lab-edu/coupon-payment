package com.couponPayment.repository;

import com.couponPayment.entity.WalletReqTb;
import com.couponPayment.entity.dto.WalletReqDto;
import com.couponPayment.entity.mapper.WalletReqMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class WalletReqRepositoryTest extends CommonReposity{
    @Autowired
    private WalletReqRepository walletReqRepository;

    @MockitoBean
    private WalletReqMapper walletReqMapper;

    private WalletReqTb walletReqTb;
    private WalletReqDto walletReqDto;

    @BeforeEach
    public void setting(){
        walletReqDto = WalletReqDto
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderId("orderId")
                .orderNum("OrderNum")
                .amount(1000)
                .build();

        walletReqTb = new WalletReqTb(null,null,walletReqDto.getMerchantId(),walletReqDto.getMerchantMemberId(),
                walletReqDto.getOrderId(),walletReqDto.getOrderNum(),walletReqDto.getAmount());

        // Mock 설정
        when(walletReqMapper.toEntity(walletReqDto)).thenReturn(walletReqTb);
        when(walletReqMapper.toDto(walletReqTb)).thenReturn(walletReqDto);

        walletReqTb = walletReqRepository.save(walletReqMapper.toEntity(walletReqDto));
        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReqTb);
    }

    @Test
    public void findById(){
        // Mock 설정
        walletReqTb = walletReqRepository.findById(1L).get();
        walletReqDto = walletReqMapper.toDto(walletReqTb);
        // 검증
        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReqTb);
    }
}