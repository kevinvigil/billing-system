package com.system.billingsystem.DTOs;

import com.system.billingsystem.entities.Product;
import org.jetbrains.annotations.NotNull;

public record ProductDto (
        Long id,
        String name,
        String description,
        double price ) {

    public ProductDto(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDto  (@NotNull Product p){
        this (p.getId(), p.getName(), p.getDescription(), p.getPrice());
    }

    @Override
    public Long id() {
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
