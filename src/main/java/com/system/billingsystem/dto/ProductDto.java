package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto (
        UUID productDtoId,
        String name,
        String description,
        BigDecimal price ) {

    public ProductDto(UUID productDtoId, String name, String description, BigDecimal price) {
        this.productDtoId = productDtoId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public UUID productDtoId() {
        return productDtoId;
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
