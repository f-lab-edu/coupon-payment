package com.couponPayment.entitiy.mapper;

import com.couponPayment.entity.MyWalletInfoTb;
import com.couponPayment.entity.StoreInfoTb;
import com.couponPayment.entity.UserInfoTb;
import com.couponPayment.entity.WalletReqTb;
import com.couponPayment.entity.dto.*;
import com.couponPayment.entity.mapper.*;
import com.couponPayment.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = "com.couponPayment.entity.mapper")
public class MapperTest {
    /**
    * 1. Mapper Test 목적
    * 2. 각 Mapper를 통하여 save 후 find하여 각 Entity <-> Dto 맵핑 되는지 테스트
    */
    @Autowired
    private MyWalletInfoRepository myWalletInfoRepository;
    @Autowired
    private MyWalletInfoMapper myWalletInfoMapper;

    @Autowired
    private StoreInfoRepository storeInfoRepository;
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    @Autowired
    private TransactionInfoRepository transactionInfoRepository;
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private WalletReqRepository walletReqRepository;
    @Autowired
    private WalletReqMapper walletReqMapper;

    @Test
    @DisplayName("WalletReq Mapper")
    public void walletReq_Mapper_Test(){
        WalletReqDto walletReqDto = WalletReqDto
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderId("orderId")
                .orderNum("OrderNum")
                .amount(1000)
                .build();

        WalletReqTb walletReqTb = walletReqRepository.save(walletReqMapper.toEntity(walletReqDto));
        walletReqDto = walletReqMapper.toDto(walletReqRepository.findById(1L).get());

        assertThat(walletReqTb.getId()).isEqualTo(1);
        assertThat(walletReqTb.getMerchantId())
                .isEqualTo(walletReqDto.getMerchantId())
                .isEqualTo("bbq");

        assertThat(walletReqTb.getMerchantMemberId())
                .isEqualTo(walletReqDto.getMerchantMemberId())
                .isEqualTo("young");

        assertThat(walletReqTb.getOrderId())
                .isEqualTo(walletReqDto.getOrderId())
                .isEqualTo("orderId");

        assertThat(walletReqTb.getAmount())
                .isEqualTo(walletReqDto.getAmount())
                .isEqualTo(1000);
    }

    @Test
    @DisplayName("UserInfo Mapper")
    public void UserInfo_Mapper_Test(){
        StoreInfoDto storeInfoDto = StoreInfoDto
                .builder()
                .merchantId("bbq")
                .tossPaymentId("toss")
                .build();
        StoreInfoTb storeInfoTb = storeInfoRepository.save(storeInfoMapper.toEntity(storeInfoDto));

        System.out.println(storeInfoTb.getId());
        UserInfoDto userInfoDto = UserInfoDto
                .builder()
                .name("young")
                .phone("010")
                .mail("naver.com")
                .useFlag(0)
                .storeInfoId(storeInfoTb.getId())
                .build();

        UserInfoTb userInfoTb = userInfoRepository.save(userInfoMapper.toEntity(userInfoDto));
        System.out.println(userInfoTb.getStoreInfoTb().getId());
        userInfoDto = userInfoMapper.toDto(userInfoRepository.findById(1L).get());


        assertThat(storeInfoRepository.findAll().size()).isEqualTo(1);

        assertThat(userInfoTb.getStoreInfoTb().getMerchantId())
                .isEqualTo(storeInfoTb.getMerchantId())
                .isEqualTo("bbq");

        assertThat(userInfoTb.getStoreInfoTb().getTossPaymentId())
                .isEqualTo(storeInfoTb.getTossPaymentId())
                .isEqualTo("toss");

        assertThat(userInfoTb.getId())
                .isEqualTo(userInfoTb.getStoreInfoTb().getId())
                .isEqualTo(1);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);

        /*assertThat(userInfoTb.getName())
                .isEqualTo(userInfoDto.getName())
                .isEqualTo("young");

        assertThat(userInfoTb.getPhone())
                .isEqualTo(userInfoDto.getPhone())
                .isEqualTo("010");

        assertThat(userInfoTb.getMail())
                .isEqualTo(userInfoDto.getMail())
                .isEqualTo("naver.com");

        assertThat(userInfoTb.getUseFlag())
                .isEqualTo(userInfoDto.getUseFlag())
                .isEqualTo(0);*/
    }

    @Test
    @DisplayName("TransactionInfo Mapper")
    public void TransactionInfo_Mapper_Test(){
        StoreInfoDto storeInfoDto = StoreInfoDto
                .builder()
                .merchantId("bbq")
                .tossPaymentId("toss")
                .build();
        StoreInfoTb storeInfoTb = storeInfoRepository.save(storeInfoMapper.toEntity(storeInfoDto));

        WalletReqDto walletReqDto = WalletReqDto
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderId("orderId")
                .orderNum("OrderNum")
                .amount(1000)
                .build();
        WalletReqTb walletReqTb = walletReqRepository.save(walletReqMapper.toEntity(walletReqDto));

        UserInfoDto userInfoDto = UserInfoDto
                .builder()
                .name("young")
                .phone("010")
                .mail("naver.com")
                .useFlag(0)
                .storeInfoId(storeInfoTb.getId())
                .build();
        UserInfoTb userInfoTb = userInfoRepository.save(userInfoMapper.toEntity(userInfoDto));

        MyWalletInfoDto myWalletInfoDto = MyWalletInfoDto
                .builder()
                .cardId("cardId")
                .cardCompany("NH")
                .cardNumber("123")
                .issuerCode("1")
                .number("2")
                .cardType("3")
                .ownerType("4")
                .userInfoId(userInfoTb.getId())
                .build();
        MyWalletInfoTb myWalletInfoTb = myWalletInfoRepository.save(myWalletInfoMapper.toEntity(myWalletInfoDto));

        TransactionInfoDto transactionInfoDto = TransactionInfoDto
                .builder()
                .tranNum("tranNum")

                .build();
    }
}
