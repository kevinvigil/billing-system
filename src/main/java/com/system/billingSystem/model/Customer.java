package com.system.billingSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private String direction;
    private String phone;
    private String email;

    public Customer() {}

    public Customer(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return ("ID: " + this.id + " Name: " + this.name);
    }
}
