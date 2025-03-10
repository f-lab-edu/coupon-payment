package com.couponPayment.repository;

import com.couponPayment.entity.StoreInfoTb;
import com.couponPayment.entity.dto.StoreInfoDto;
import com.couponPayment.entity.mapper.StoreInfoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class StoreInfoRepositoryTest extends CommonReposity{
    @Autowired
    private StoreInfoRepository storeInfoRepository;
    @MockitoBean
    private StoreInfoMapper storeInfoMapper;

    private StoreInfoDto storeInfoDto;
    private StoreInfoTb storeInfoTb;

    @BeforeEach
    public void setting(){
        storeInfoDto = StoreInfoDto
                .builder()
                .merchantId("bbq")
                .tossPaymentId("toss")
                .build();
        storeInfoTb = new StoreInfoTb(
                null,storeInfoDto.getMerchantId(),storeInfoDto.getTossPaymentId(),null,null
        );

        // Mock 설정
        when(storeInfoMapper.toEntity(storeInfoDto)).thenReturn(storeInfoTb);
        when(storeInfoMapper.toDto(storeInfoTb)).thenReturn(storeInfoDto);

        storeInfoTb = storeInfoRepository.save(storeInfoMapper.toEntity(storeInfoDto));
        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);
    }

    @Test
    public void findById(){
        storeInfoTb = storeInfoRepository.findById(1L).get();
        storeInfoDto = storeInfoMapper.toDto(storeInfoTb);

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);
    }
}