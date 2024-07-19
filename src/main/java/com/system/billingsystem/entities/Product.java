package com.system.billingsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private UUID id;

    private String name;
    private String description;

    private double price;

    public Product() {}

    public Product(UUID id) {
        this.id=id;
    }

    @Override
    public String toString(){
        return ("Product { " +
                " id: " + this.id +
                ", Name: " + this.name +
                ", Description: " + this.description +
                ", Price: " + this.price +
                " }");
    }

}

