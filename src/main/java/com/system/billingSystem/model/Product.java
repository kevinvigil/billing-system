package com.system.billingSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column
    private String description;

    @Column(nullable = false)
    private double price;

    public Product() {}

    public Product(Long id) {
        this.id=id;
    }

    @Override
    public String toString(){
        return ("ID: " + this.id + " Name: " + this.name);
    }
}
