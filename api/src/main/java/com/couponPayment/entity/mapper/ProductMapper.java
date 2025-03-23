package com.couponPayment.entity.mapper;

import com.couponPayment.entity.Product;
import com.couponPayment.entity.dto.ProductDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {
    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);
}
