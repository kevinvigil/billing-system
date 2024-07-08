package com.system.billingSystem.model;

import com.system.billingSystem.dto.CustomerDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if ( (!Objects.equals(id, customer.id)) || (!Objects.equals(name, customer.name)) || (!Objects.equals(direction, customer.direction))
        || (!Objects.equals(phone, customer.phone)) || (!Objects.equals(cuit, customer.cuit)) )return false;
        return Objects.equals(email, customer.email);
    }
}
