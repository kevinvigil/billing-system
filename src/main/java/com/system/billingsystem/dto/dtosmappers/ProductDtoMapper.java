package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.ProductDto;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;

public class ProductDtoMapper {

    private ProductDtoMapper(){}

    public static ProductDto toDto(Product product){
        return new ProductDto(
                product.getProductId().getValue(),
                product.getName().getName(),
                product.getDescription(),
                product.getPrice().getValue()
        );
    }

    public static Product toDomain(ProductDto productDto){
        Product product = new Product();

        product.setProductId(new ProductId(productDto.productId()));
        product.setName(new ProductName(productDto.name()));
        product.setDescription(productDto.description());
        product.setPrice(new ProductPrice(productDto.price()));

        return product;
    }
}
