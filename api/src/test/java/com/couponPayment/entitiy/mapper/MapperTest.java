package com.couponPayment.entitiy.mapper;

import com.couponPayment.entity.MyWalletInfo;
import com.couponPayment.entity.StoreInfo;
import com.couponPayment.entity.TransactionInfo;
import com.couponPayment.entity.UserInfo;
import com.couponPayment.entity.WalletReq;
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

        WalletReq walletReq = new WalletReq(1L, null, "bbq", "young", "orderId", "orderNum", 1000);
        WalletReqDto walletReqDto = walletReqMapper.toDto(walletReq);

        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReq);
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
        WalletReq walletReq = walletReqMapper.toEntity(walletReqDto);

        assertThat(walletReqDto)
                .usingRecursiveComparison()
                .isEqualTo(walletReq);
    }

    @Test
    @DisplayName("StoreInfo Mapper EntityToDto")
    public void storeInfo_Mapper_EntityToDto() {
        StoreInfo storeInfo = new StoreInfo(1L, "bbq", "toss", null, null);
        StoreInfoDto storeInfoDto = storeInfoMapper.toDto(storeInfo);

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfo);
    }

    @Test
    @DisplayName("StoreInfo Mapper DtoToEntity")
    public void storeInfo_Mapper_DtoToEntity() {
        StoreInfoDto storeInfoDto = StoreInfoDto
                .builder()
                .merchantId("bbq")
                .tossPaymentId("toss")
                .build();
        StoreInfo storeInfo = storeInfoMapper.toEntity(storeInfoDto);

        assertThat(storeInfoDto)
                .usingRecursiveComparison()
                .isEqualTo(storeInfo);
    }

    @Test
    @DisplayName("UserInfo Mapper EntityToDto")
    public void UserInfo_Mapper_EntityToDto() {
        StoreInfo storeInfo = new StoreInfo(1L, "bbq", "toss", null, null);

        UserInfo userInfo = new UserInfo(1L, storeInfo, "young", "010", "naver.com", 0, "young","1234",null, null);
        UserInfoDto userInfoDto = userInfoMapper.toDto(userInfo);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfo);

        assertThat(userInfoDto.getStoreInfoId())
                .isEqualTo(userInfo.getStoreInfo().getId());
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
        UserInfo userInfo = userInfoMapper.toEntity(userInfoDto);

        assertThat(userInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("storeInfoId")
                .isEqualTo(userInfo);

        assertThat(userInfoDto.getStoreInfoId())
                .isEqualTo(userInfo.getStoreInfo().getId());
    }

    @Test
    @DisplayName("MyWalletInfo Mapper EntityToDto")
    public void MyWalletInfo_Mapper_EntityToDto() {
        UserInfo userInfo = new UserInfo(1L, null, "young", "010", "naver.com", 0, "young","1234",null, null);

        MyWalletInfo myWalletInfo = new MyWalletInfo(1L, userInfo, "cardId", "cardCompany", "cardNumber", "issuerCode", "acquirerCode", "number", "cardType", "ownerType",0, null);
        MyWalletInfoDto myWalletInfoDto = myWalletInfoMapper.toDto(myWalletInfo);

        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfo);
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
        MyWalletInfo myWalletInfo = myWalletInfoMapper.toEntity(myWalletInfoDto);

        assertThat(myWalletInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("userInfoId")
                .isEqualTo(myWalletInfo);
    }


    @Test
    @DisplayName("TransactionInfo Mapper EntityToDto")
    public void TransactionInfo_Mapper_EntityToEntity() {
        WalletReq walletReq = new WalletReq(1L, null, "bbq", "young", "orderId", "orderNum", 1000);
        StoreInfo storeInfo = new StoreInfo(1L, "bbq", "toss", null, null);
        UserInfo userInfo = new UserInfo(1L, storeInfo, "young", "010", "naver.com", 0, "young","1234",null, null);
        MyWalletInfo myWalletInfo = new MyWalletInfo(1L, userInfo, "cardId", "cardCompany", "cardNumber", "issuerCode", "acquirerCode", "number", "cardType", "ownerType", 0,null);

        TransactionInfo transactionInfo = new TransactionInfo(1L, myWalletInfo, walletReq, storeInfo, userInfo
                , "tranNum", "2025-03-10T09:23:27+09:00", 1000,1000, "2025-03-10T09:23:27+09:00", "approvalNum",
                100, "2025-03-10T09:23:27+09:00", 0, "callBackUrl", "DONE");

        TransactionInfoDto transactionInfoDto = transactionMapper.toDto(transactionInfo);

        assertThat(transactionInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("myWalletInfoId", "userInfoId", "storeInfoId", "walletReqId")
                .isEqualTo(transactionInfo);

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
                .amount(1000)
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
                .status("DONE")
                .build();

        TransactionInfo transactionInfo = transactionMapper.toEntity(transactionInfoDto);
        assertThat(transactionInfoDto)
                .usingRecursiveComparison()
                .ignoringFields("myWalletInfoId", "userInfoId", "storeInfoId", "walletReqId")
                .isEqualTo(transactionInfo);

        assertThat(transactionInfo.getMyWalletInfo().getId()).isEqualTo(1L);
        assertThat(transactionInfo.getUserInfo().getId()).isEqualTo(1L);
        assertThat(transactionInfo.getStoreInfo().getId()).isEqualTo(1L);
        assertThat(transactionInfo.getWalletReq().getId()).isEqualTo(1L);
    }
}
