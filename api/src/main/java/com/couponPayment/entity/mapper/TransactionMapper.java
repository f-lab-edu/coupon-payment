package com.couponPayment.entity.mapper;

import com.couponPayment.entity.TransactionInfoTb;
import com.couponPayment.entity.dto.TransactionInfoDto;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    /*@Mapping(source = "requestDt", target = "requestDt", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "approvalDt", target = "approvalDt", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "cancelDt", target = "cancelDt", qualifiedByName = "stringToTimestamp")*/
    @Mapping(source="userInfoId", target = "userInfoTb.id")
    @Mapping(source="storeInfoId", target = "storeInfoTb.id")
    @Mapping(source="myWalletInfoId", target = "myWalletInfoTb.id")
    @Mapping(source="walletReqId", target = "walletReqTb.id")
    TransactionInfoTb toEntity(TransactionInfoDto transactionInfoDto);

    /*@Mapping(source = "requestDt", target = "requestDt", qualifiedByName = "timestampToString")
    @Mapping(source = "approvalDt", target = "approvalDt", qualifiedByName = "timestampToString")
    @Mapping(source = "cancelDt", target = "cancelDt", qualifiedByName = "timestampToString")*/
    @Mapping(source="myWalletInfoTb.id", target = "myWalletInfoId")
    @Mapping(source="walletReqTb.id", target = "walletReqId")
    @Mapping(source="storeInfoTb.id", target = "storeInfoId")
    @Mapping(source="userInfoTb.id", target = "userInfoId")
    TransactionInfoDto toDto(TransactionInfoTb transactionInfoTb);

    /*@Named("stringToTimestamp")
    default Timestamp stringToTimestamp(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        // ISO 8601 형식으로 OffsetDateTime 파싱
        OffsetDateTime odt = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // 시간대 정보를 제거하고 LocalDateTime으로 변환 (UTC+9를 기준으로 로컬 시간을 설정)
        LocalDateTime localDateTime = odt.toLocalDateTime();

        // LocalDateTime을 Timestamp로 변환하여 반환
        return Timestamp.valueOf(localDateTime);
    }

    @Named("timestampToString")
    default String timestampToString(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        // Timestamp를 LocalDateTime으로 변환
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        // LocalDateTime을 문자열로 변환 (ISO 8601 형식에서 T를 제거)
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }*/
}
