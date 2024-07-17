package com.system.billingsystem.dto;

import java.util.UUID;

public record ProductDto (
        UUID id,
        String name,
        String description,
        double price ) {

    public ProductDto(UUID id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public UUID id() {
        return id;
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
    public double price() {
        return price;
    }
}
