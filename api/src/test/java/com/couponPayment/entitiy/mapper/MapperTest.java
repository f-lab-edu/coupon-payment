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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {
    /**
     * 1. Mapper Test 목적
     * 2. 각 Mapper를 통하여 save 후 find하여 각 Entity <-> Dto 맵핑 되는지 테스트
     */
    private WalletReqMapper walletReqMapper = Mappers.getMapper(WalletReqMapper.class);
    private StoreInfoMapper storeInfoMapper = Mappers.getMapper(StoreInfoMapper.class);
    private UserInfoMapper userInfoMapper = Mappers.getMapper(UserInfoMapper.class);
    private MyWalletInfoMapper myWalletInfoMapper = Mappers.getMapper(MyWalletInfoMapper.class);
    private TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Test
    @DisplayName("WalletReq Mapper EntityToDto")
    public void walletReq_Mapper_EntityToDto() {

        WalletReqTb walletReqTb = new WalletReqTb(1L, null, "bbq", "young", "orderId", "orderNum", 1000);
        WalletReqDto walletReqDto = walletReqMapper.toDto(walletReqTb);

        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReqTb);
    }

    @Test
    @DisplayName("WalletReq Mapper DtoToEntity")
    public void walletReq_Mapper_DtoToEntity() {

        WalletReqDto walletReqDto = WalletReqDto
                .builder()
                .merchantId("bbq")
                .merchantMemberId("young")
                .orderId("orderId")
                .orderNum("OrderNum")
                .amount(1000)
                .build();
        WalletReqTb walletReqTb = walletReqMapper.toEntity(walletReqDto);

        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReqTb);
    }

    @Test
    @DisplayName("StoreInfo Mapper EntityToDto")
    public void storeInfo_Mapper_EntityToDto() {
        StoreInfoTb storeInfoTb = new StoreInfoTb(1L, "bbq", "toss", null, null);
        StoreInfoDto storeInfoDto = storeInfoMapper.toDto(storeInfoTb);

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);
    }

    @Test
    @DisplayName("StoreInfo Mapper DtoToEntity")
    public void storeInfo_Mapper_DtoToEntity() {
        StoreInfoDto storeInfoDto = StoreInfoDto
                .builder()
                .merchantId("bbq")
                .tossPaymentId("toss")
                .build();
        StoreInfoTb storeInfoTb = storeInfoMapper.toEntity(storeInfoDto);

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfoTb);
    }

    @Test
    @DisplayName("UserInfo Mapper EntityToDto")
    public void UserInfo_Mapper_EntityToDto() {
        StoreInfoTb storeInfoTb = new StoreInfoTb(1L, "bbq", "toss", null, null);

        UserInfoTb userInfoTb = new UserInfoTb(1L, storeInfoTb, "young", "010", "naver.com", 0, null, null);
        UserInfoDto userInfoDto = userInfoMapper.toDto(userInfoTb);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);

        assertThat(userInfoDto.getStoreInfoId())
                .isEqualTo(userInfoTb.getStoreInfoTb().getId());
    }

    @Test
    @DisplayName("UserInfo Mapper DtoToEntity")
    public void UserInfo_Mapper_DtoToEntity() {
        UserInfoDto userInfoDto = UserInfoDto
                .builder()
                .name("young")
                .phone("010")
                .mail("naver.com")
                .useFlag(0)
                .storeInfoId(1L)
                .build();
        UserInfoTb userInfoTb = userInfoMapper.toEntity(userInfoDto);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfoTb);

        assertThat(userInfoDto.getStoreInfoId())
                .isEqualTo(userInfoTb.getStoreInfoTb().getId());
    }

    @Test
    @DisplayName("MyWalletInfo Mapper EntityToDto")
    public void MyWalletInfo_Mapper_EntityToDto() {
        UserInfoTb userInfoTb = new UserInfoTb(1L, null, "young", "010", "naver.com", 0, null, null);

        MyWalletInfoTb myWalletInfoTb = new MyWalletInfoTb(1L, userInfoTb, "cardId", "cardCompany", "cardNumber", "issuerCode", "acquirerCode", "number", "cardType", "ownerType", null);
        MyWalletInfoDto myWalletInfoDto = myWalletInfoMapper.toDto(myWalletInfoTb);

        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfoTb);
    }

    @Test
    @DisplayName("MyWalletInfo Mapper DtoToEntity")
    public void MyWalletInfo_Mapper_DtoToEntity() {
        MyWalletInfoDto myWalletInfoDto = MyWalletInfoDto
                .builder()
                .cardId("cardId")
                .cardCompany("NH")
                .cardNumber("123")
                .issuerCode("1")
                .number("2")
                .cardType("3")
                .ownerType("4")
                .userInfoId(1L)
                .build();
        MyWalletInfoTb myWalletInfoTb = myWalletInfoMapper.toEntity(myWalletInfoDto);

        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfoTb);
    }


    @Test
    @DisplayName("TransactionInfo Mapper EntityToDto")
    public void TransactionInfo_Mapper_EntityToEntity() {
        WalletReqTb walletReqTb = new WalletReqTb(1L, null, "bbq", "young", "orderId", "orderNum", 1000);
        StoreInfoTb storeInfoTb = new StoreInfoTb(1L, "bbq", "toss", null, null);
        UserInfoTb userInfoTb = new UserInfoTb(1L, storeInfoTb, "young", "010", "naver.com", 0, null, null);
        MyWalletInfoTb myWalletInfoTb = new MyWalletInfoTb(1L, userInfoTb, "cardId", "cardCompany", "cardNumber", "issuerCode", "acquirerCode", "number", "cardType", "ownerType", null);

        TransactionInfoTb transactionInfoTb = new TransactionInfoTb(1L, myWalletInfoTb, walletReqTb, storeInfoTb, userInfoTb
                , "tranNum", "2025-03-10T09:23:27+09:00", 1000, "2025-03-10T09:23:27+09:00", "approvalNum",
                100, "2025-03-10T09:23:27+09:00", 0, "callBackUrl");

        TransactionInfoDto transactionInfoDto = transactionMapper.toDto(transactionInfoTb);

        assertThat(transactionInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("myWalletInfoId", "userInfoId", "storeInfoId", "walletReqId")
                .isEqualTo(transactionInfoTb);

        assertThat(transactionInfoDto.getMyWalletInfoId()).isEqualTo(1L);
        assertThat(transactionInfoDto.getUserInfoId()).isEqualTo(1L);
        assertThat(transactionInfoDto.getStoreInfoId()).isEqualTo(1L);
        assertThat(transactionInfoDto.getWalletReqId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("TransactionInfo Mapper DtoToEntitiy")
    public void TransactionInfo_Mapper_DtoToEntitiy() {

        TransactionInfoDto transactionInfoDto = TransactionInfoDto
                .builder()
                .tranNum("tranNum")
                .requestDt("2025-03-10T09:23:27+09:00")
                .approvalAmount(1000)
                .approvalDt("2025-03-10T09:23:27+09:00")
                .approvalNum("approvalNum")
                .cancelAmount(1000)
                .cancelDt("2025-03-10T09:23:27+09:00")
                .installment(0)
                .callbackUrl("https://www.naver.com")
                .myWalletInfoId(1L)
                .userInfoId(1L)
                .storeInfoId(1L)
                .walletReqId(1L)
                .build();

        TransactionInfoTb transactionInfoTb = transactionMapper.toEntity(transactionInfoDto);
        assertThat(transactionInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("myWalletInfoId", "userInfoId", "storeInfoId", "walletReqId")
                .isEqualTo(transactionInfoTb);

        assertThat(transactionInfoTb.getMyWalletInfoTb().getId()).isEqualTo(1L);
        assertThat(transactionInfoTb.getUserInfoTb().getId()).isEqualTo(1L);
        assertThat(transactionInfoTb.getStoreInfoTb().getId()).isEqualTo(1L);
        assertThat(transactionInfoTb.getWalletReqTb().getId()).isEqualTo(1L);
    }
}
