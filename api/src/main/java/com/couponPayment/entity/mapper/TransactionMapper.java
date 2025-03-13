package com.couponPayment.entity.mapper;

import com.couponPayment.entity.TransactionInfo;
import com.couponPayment.entity.dto.TransactionInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    /*@Mapping(source = "requestDt", target = "requestDt", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "approvalDt", target = "approvalDt", qualifiedByName = "stringToTimestamp")
    @Mapping(source = "cancelDt", target = "cancelDt", qualifiedByName = "stringToTimestamp")*/
    @Mapping(source="userInfoId", target = "userInfo.id")
    @Mapping(source="storeInfoId", target = "storeInfo.id")
    @Mapping(source="myWalletInfoId", target = "myWalletInfo.id")
    @Mapping(source="walletReqId", target = "walletReq.id")
    TransactionInfo toEntity(TransactionInfoDto transactionInfoDto);

    /*@Mapping(source = "requestDt", target = "requestDt", qualifiedByName = "timestampToString")
    @Mapping(source = "approvalDt", target = "approvalDt", qualifiedByName = "timestampToString")
    @Mapping(source = "cancelDt", target = "cancelDt", qualifiedByName = "timestampToString")*/
    @Mapping(source= "myWalletInfo.id", target = "myWalletInfoId")
    @Mapping(source= "walletReq.id", target = "walletReqId")
    @Mapping(source= "storeInfo.id", target = "storeInfoId")
    @Mapping(source= "userInfo.id", target = "userInfoId")
    TransactionInfoDto toDto(TransactionInfo transactionInfo);

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
