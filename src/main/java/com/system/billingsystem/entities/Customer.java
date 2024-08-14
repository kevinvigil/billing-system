package com.system.billingsystem.entities;

import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private CustomerId customerId;
    private String name;
    private String password;

    @Email
    private String email;

    private Company company;

    public Customer() {}

    public Customer(CustomerId customerId, Company company, String email, String password, String name) {
        this.customerId = customerId;
        this.company = company;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString(){
        return ("Customer { " +
                ", customer_id: " + this.customerId +
                ", Name: " + this.name +
                " }"
        );
    }
}
