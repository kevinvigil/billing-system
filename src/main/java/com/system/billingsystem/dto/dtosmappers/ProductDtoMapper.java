package com.system.billingSystem.dto.dtosmappers;

import com.system.billingSystem.dto.ProductDto;
import com.system.billingSystem.entities.Product;

public class ProductDtoMapper {

    private ProductDtoMapper(){}

    public static ProductDto toDto(Product product){
        return new ProductDto(
                product.getProduct_id(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product toDomain(ProductDto productDto){
        Product product = new Product();

        product.setProduct_id(productDto.productDto_id());
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());

        return product;
    }
}
