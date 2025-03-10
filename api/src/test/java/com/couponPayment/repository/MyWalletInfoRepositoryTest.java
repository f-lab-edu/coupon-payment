package com.couponPayment.repository;

import com.couponPayment.entity.MyWalletInfoTb;
import com.couponPayment.entity.dto.MyWalletInfoDto;
import com.couponPayment.entity.mapper.MyWalletInfoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class MyWalletInfoRepositoryTest extends CommonReposity{
    @Autowired
    private MyWalletInfoRepository myWalletInfoRepository;

    @MockitoBean
    private MyWalletInfoMapper myWalletInfoMapper;

    private MyWalletInfoTb myWalletInfoTb;
    private MyWalletInfoDto myWalletInfoDto;
    @BeforeEach
    public void setting(){
        myWalletInfoDto = MyWalletInfoDto
                .builder()
                .cardId("cardId")
                .cardCompany("NH")
                .cardNumber("123")
                .issuerCode("1")
                .acquirerCode("5")
                .number("2")
                .cardType("3")
                .ownerType("4")
                .userInfoId(1L)
                .build();
        myWalletInfoTb = new MyWalletInfoTb(
                null,  // ID (자동 생성)
                null,
                myWalletInfoDto.getCardId(),
                myWalletInfoDto.getCardCompany(),
                myWalletInfoDto.getCardNumber(),
                myWalletInfoDto.getIssuerCode(),
                myWalletInfoDto.getAcquirerCode(),
                myWalletInfoDto.getNumber(),
                myWalletInfoDto.getCardType(),
                myWalletInfoDto.getOwnerType(),
                null
        );

        // Mock 설정
        when(myWalletInfoMapper.toEntity(myWalletInfoDto)).thenReturn(myWalletInfoTb);
        when(myWalletInfoMapper.toDto(myWalletInfoTb)).thenReturn(myWalletInfoDto);

        myWalletInfoTb = myWalletInfoRepository.save(myWalletInfoMapper.toEntity(myWalletInfoDto));
        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfoTb);
    }

    @Test
    public void findById(){
        // Mock 설정
        myWalletInfoTb = myWalletInfoRepository.findById(1L).get();
        myWalletInfoDto = myWalletInfoMapper.toDto(myWalletInfoTb);
        // 검증
        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfoTb);
    }
}