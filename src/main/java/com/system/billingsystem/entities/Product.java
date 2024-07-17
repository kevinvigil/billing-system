package com.system.billingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column
    private String description;

    @Column(nullable = false)
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

