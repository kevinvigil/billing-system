package com.system.billingsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private UUID id;

    private String name;

    private String password;

    private String email;

    private Company company;

    public Customer() {}

    @Override
    public String toString(){
        return ("Customer { " +
                ", id: " + this.id +
                ", Name: " + this.name +
                " }"
        );
    }
}
