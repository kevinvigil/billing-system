package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto (
        UUID productId,
        String name,
        Integer count,
        String description,
        BigDecimal price ) {

    public ProductDto(UUID productId, String name,  Integer count, String description, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.count = count;
        this.description = description;
        this.price = price;
    }

    public UUID productId() {
        return productId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ProductDto productDto){
            return !(!productId.equals(productDto.productId()) ||
                    !name.equals(productDto.name()) ||
                    !description.equals(productDto.description()));
        }
        return false;
    }
}
