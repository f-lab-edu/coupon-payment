package com.couponPayment.entitiy.mapper;

import com.couponPayment.entity.MyWalletInfoTb;
import com.couponPayment.entity.StoreInfoTb;
import com.couponPayment.entity.TransactionInfoTb;
import com.couponPayment.entity.UserInfoTb;
import com.couponPayment.entity.WalletReqTb;
import com.couponPayment.entity.dto.MyWalletInfoDto;
import com.couponPayment.entity.dto.StoreInfoDto;
import com.couponPayment.entity.dto.TransactionInfoDto;
import com.couponPayment.entity.dto.UserInfoDto;
import com.couponPayment.entity.dto.WalletReqDto;
import com.couponPayment.entity.mapper.MyWalletInfoMapper;
import com.couponPayment.entity.mapper.StoreInfoMapper;
import com.couponPayment.entity.mapper.TransactionMapper;
import com.couponPayment.entity.mapper.UserInfoMapper;
import com.couponPayment.entity.mapper.WalletReqMapper;
import com.couponPayment.repository.MyWalletInfoRepository;
import com.couponPayment.repository.StoreInfoRepository;
import com.couponPayment.repository.TransactionInfoRepository;
import com.couponPayment.repository.UserInfoRepository;
import com.couponPayment.repository.WalletReqRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {
    /**
    * 1. Mapper Test 목적
    * 2. 각 Mapper를 통하여 save 후 find하여 각 Entity <-> Dto 맵핑 되는지 테스트
    */


    /*@Autowired
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

    private WalletReqTb walletReqTb;
    private WalletReqDto walletReqDto;
    private StoreInfoTb storeInfoTb;
    private StoreInfoDto storeInfoDto;
    private UserInfoTb userInfoTb;
    private UserInfoDto userInfoDto;
    private MyWalletInfoTb myWalletInfoTb;
    private MyWalletInfoDto myWalletInfoDto;
    @BeforeEach
    private void setting(){
        walletReqDto = WalletReqDto
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderId("orderId")
                .orderNum("OrderNum")
                .amount(1000)
                .build();
        walletReqTb = walletReqRepository.save(walletReqMapper.toEntity(walletReqDto));


        storeInfoDto = StoreInfoDto
                .builder()
                .merchantId("bbq")
                .tossPaymentId("toss")
                .build();
        storeInfoTb = storeInfoRepository.save(storeInfoMapper.toEntity(storeInfoDto));

         userInfoDto = UserInfoDto
                .builder()
                .name("young")
                .phone("010")
                .mail("naver.com")
                .useFlag(0)
                .storeInfoId(storeInfoTb.getId())
                .build();
        userInfoTb = userInfoRepository.save(userInfoMapper.toEntity(userInfoDto));

        myWalletInfoDto = MyWalletInfoDto
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

        myWalletInfoTb = myWalletInfoRepository.save(myWalletInfoMapper.toEntity(myWalletInfoDto));
    }*/


    private WalletReqMapper walletReqMapper = Mappers.getMapper(WalletReqMapper.class);
    private StoreInfoMapper storeInfoMapper = Mappers.getMapper(StoreInfoMapper.class);
    private UserInfoMapper userInfoMapper = Mappers.getMapper(UserInfoMapper.class);

    @Test
    @DisplayName("WalletReq Mapper")
    public void walletReq_Mapper_Test(){

        WalletReqTb walletReqTb = new WalletReqTb(1L, null, "bbq","young", "orderId", "orderNum",1000);
        WalletReqDto walletReqDto = walletReqMapper.toDto(walletReqTb);

        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReqTb);
    }

    @Test
    @DisplayName("StoreInfo Mapper")
    public void storeInfo_Mapper_Test(){
        StoreInfoTb storeInfoTb = new StoreInfoTb(1L, "bbq", "toss", null, null);
        StoreInfoDto storeInfoDto = storeInfoMapper.toDto(storeInfoTb);

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);
    }

    @Test
    @DisplayName("UserInfo Mapper")
    public void UserInfo_Mapper_Test(){
        StoreInfoTb storeInfoTb = new StoreInfoTb(1L, "bbq", "toss", null, null);
        StoreInfoDto storeInfoDto = storeInfoMapper.toDto(storeInfoTb);

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);

        UserInfoTb userInfoTb = new UserInfoTb(1L,storeInfoTb,"young","010","naver.com",0,null,null);
        UserInfoDto userInfoDto = userInfoMapper.toDto(userInfoTb);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);

        /*
        UserInfoTb userInfoTb = userInfoMapper.toEntity(userInfoDto);
        userInfoDto = userInfoMapper.toDto(userInfoTb);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(userInfoTb);*/
    }

    @Test
    @DisplayName("MyWalletInfo Mapper")
    public void MyWalletInfo_Mapper_Test(){
        /*StoreInfoTb storeInfoTb = storeInfoMapper.toEntity(storeInfoDto);
        userInfoDto = UserInfoDto
                .builder()
                .name("young")
                .phone("010")
                .mail("naver.com")
                .useFlag(0)
                .storeInfoId(storeInfoTb.getId())
                .build();

        UserInfoTb userInfoTb = userInfoMapper.toEntity(userInfoDto);
        userInfoDto = userInfoMapper.toDto(userInfoTb);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(userInfoTb);*/
    }
    /*@Test
    @DisplayName("UserInfo Mapper")
    public void UserInfo_Mapper_Test(){

        storeInfoDto = storeInfoMapper.toDto(storeInfoRepository.findById(storeInfoTb.getId()).get());
        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);

        userInfoDto = userInfoMapper.toDto(userInfoRepository.findById(userInfoTb.getId()).get());

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);


        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);
    }

    @Test
    @DisplayName("TransactionInfo Mapper")
    public void TransactionInfo_Mapper_Test(){
        *//*StoreInfoDto storeInfoDto = StoreInfoDto
                .builder()
                .merchantId("bbq")
                .tossPaymentId("toss")
                .build();
        StoreInfoTb storeInfoTb = storeInfoRepository.save(storeInfoMapper.toEntity(storeInfoDto));*//*
        storeInfoDto = storeInfoMapper.toDto(storeInfoRepository.findById(storeInfoTb.getId()).get());
        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);

        *//*WalletReqDto walletReqDto = WalletReqDto
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderId("orderId")
                .orderNum("OrderNum")
                .amount(1000)
                .build();
        WalletReqTb walletReqTb = walletReqRepository.save(walletReqMapper.toEntity(walletReqDto));*//*
        walletReqDto = walletReqMapper.toDto(walletReqRepository.findById(walletReqTb.getId()).get());
        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReqTb);

*//*        UserInfoDto userInfoDto = UserInfoDto
                .builder()
                .name("young")
                .phone("010")
                .mail("naver.com")
                .useFlag(0)
                .storeInfoId(storeInfoTb.getId())
                .build();
        UserInfoTb userInfoTb = userInfoRepository.save(userInfoMapper.toEntity(userInfoDto));*//*
        userInfoDto = userInfoMapper.toDto(userInfoRepository.findById(userInfoTb.getId()).get());

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);
        //userInfoTb 안에 storeInfoTb가 잘 맵핑 되었는지 확인
        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoRepository.findById(userInfoDto.getStoreInfoId()).get());



        myWalletInfoDto = myWalletInfoMapper.toDto(myWalletInfoRepository.findById(myWalletInfoTb.getId()).get());

        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfoTb);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoRepository.findById(myWalletInfoDto.getUserInfoId()).get());


        TransactionInfoDto transactionInfoDto = TransactionInfoDto
                .builder()
                .tranNum("tranNum")
                .requestDt("2025-03-10T09:23:27+09:00")
                .approvalAmount(1000)
                .approvalDt("2025-03-10T09:23:27+09:00")
                .cancelAmount(1000)
                .cancelDt("2025-03-10T09:23:27+09:00")
                .installment(0)
                .callbackUrl("https://www.naver.com")
                .myWalletInfoId(myWalletInfoTb.getId())
                .userInfoId(userInfoTb.getId())
                .storeInfoId(storeInfoTb.getId())
                .walletReqId(walletReqTb.getId())
                .build();

        TransactionInfoTb transactionInfoTb = transactionInfoRepository.save(transactionMapper.toEntity(transactionInfoDto));
        transactionInfoDto = transactionMapper.toDto(transactionInfoRepository.findById(transactionInfoTb.getId()).get());

        assertThat(transactionInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("myWalletInfoId","userInfoId","storeInfoId" ,"walletReqId","requestDt","approvalDt","cancelDt")
                .isEqualTo(transactionInfoTb);
        assertThat(transactionInfoDto.getApprovalDt())
                .isEqualTo("2025-03-10 09:23:27");

        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfoRepository.findById(transactionInfoDto.getMyWalletInfoId()).get());

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoRepository.findById(transactionInfoDto.getUserInfoId()).get());

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(storeInfoRepository.findById(transactionInfoDto.getStoreInfoId()).get());

        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReqRepository.findById(transactionInfoDto.getUserInfoId()).get());
    }*/
}
