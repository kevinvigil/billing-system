package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.ProductDto;
import com.system.billingsystem.entities.Product;

public class ProductDtoMapper {

    private ProductDtoMapper(){}

    public static ProductDto toDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product toDomain(ProductDto productDto){
        Product product = new Product();

        product.setId(productDto.id());
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());

        return product;
    }
}
