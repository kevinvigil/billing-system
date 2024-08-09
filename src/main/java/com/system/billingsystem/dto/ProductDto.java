package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto (
        UUID productId,
        String name,
        String description,
        BigDecimal price ) {

    public ProductDto(UUID productId, String name, String description, BigDecimal price) {
        this.productId = productId;
        this.name = name;
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
}
