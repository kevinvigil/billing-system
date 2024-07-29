package com.system.billingsystem.entities;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private UUID customer_id;
    private String name;
    private String password;
    
    @Email
    private String email;

    private Company company;

    public Customer() {}

    @Override
    public String toString(){
        return ("Customer { " +
                ", customer_id: " + this.customer_id +
                ", Name: " + this.name +
                " }"
        );
    }
}
