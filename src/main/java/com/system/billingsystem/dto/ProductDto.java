package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto (
        UUID productDto_id,
        String name,
        String description,
        BigDecimal price ) {

    public ProductDto(UUID productDto_id, String name, String description, BigDecimal price) {
        this.productDto_id = productDto_id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public UUID productDto_id() {
        return productDto_id;
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
