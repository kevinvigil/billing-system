package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.ProductDto;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(target = "productId", expression = "java(mapProductId(product))"),
            @Mapping(target = "name", expression = "java(mapProductName(product))"),
            @Mapping(target = "description", source = "product.description"),
            @Mapping(target = "price", expression = "java(mapProductPrice(product))"),
            @Mapping(target = "count", source = "count")
    })
    ProductDto toDto(Product product);

    @Mappings({
            @Mapping(target = "productId", expression = "java(mapProductId(dto))"),
            @Mapping(target = "name", expression = "java(mapProductName(dto))"),
            @Mapping(target = "description", source = "dto.description"),
            @Mapping(target = "price", expression = "java(mapProductPrice(dto))"),
            @Mapping(target = "count", source = "count")
    })
    Product toDomain(ProductDto dto);

    default UUID mapProductId(Product product) {
        return product.getProductId() != null ? product.getProductId().getValue() : null;
    }

    default ProductId mapProductId(ProductDto dto) {
        return new ProductId(dto.productId());
    }

    default String mapProductName(Product product) {
        return product.getName() != null ? product.getName().getName() : null;
    }

    default ProductName mapProductName(ProductDto dto) {
        return new ProductName(dto.name());
    }

    default BigDecimal mapProductPrice(Product product) {
        return product.getPrice() != null ? product.getPrice().getValue() : null;
    }

    default ProductPrice mapProductPrice(ProductDto dto) {
        return new ProductPrice(dto.price());
    }
}

