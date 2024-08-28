package com.javalearn.product.mapper;

import com.javalearn.product.dto.ProductRequestDto;
import com.javalearn.product.dto.ProductResponseDto;
import com.javalearn.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    Product  mapProductRequestDtoToProduct(ProductRequestDto productRequestDto);

    @Mapping(source = "product.ID", target = "ID")
    ProductResponseDto mapProductToProductResponseDto(Product product);

}
