package com.system.billingsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private UUID productId;
    private String name;
    private String description;
    private BigDecimal price;

    public Product() {}

    public Product(UUID productId) {
        this.productId = productId;
    }

    @Override
    public String toString(){
        return ("Product { " +
                " product_id: " + this.productId +
                ", Name: " + this.name +
                ", Description: " + this.description +
                ", Price: " + this.price +
                " }");
    }
}

