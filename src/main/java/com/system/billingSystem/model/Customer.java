package com.system.billingSystem.model;

import com.system.billingSystem.dto.CustomerDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @Column(unique = true)
    private String cuit;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    public Customer() {}

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(CustomerDto customerDto){
        this.id = customerDto.id();
        this.name = customerDto.name();
        this.email = customerDto.email();
        this.phone = customerDto.phone();
        this.direction = customerDto.direction();
    }

    @Override
    public String toString(){
        return ("ID: " + this.id + " Name: " + this.name);
    }
}
