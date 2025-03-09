package com.couponPayment.entity.mapper;

import com.couponPayment.entity.StoreInfoTb;
import com.couponPayment.repository.StoreInfoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreInfoMapperHelper {
    private final StoreInfoRepository storeInfoRepository;

    @Named("mapStoreInfo")
    public StoreInfoTb mapStoreInfo(Long storeInfoId){
        return storeInfoRepository.findById(storeInfoId).orElse(null);
    }
}
