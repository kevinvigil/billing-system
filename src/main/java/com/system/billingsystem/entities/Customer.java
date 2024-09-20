package com.system.billingsystem.entities;

import com.system.billingsystem.entities.microtypes.Mail;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.entities.microtypes.names.CustomerName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private CustomerId customerId;
    private CustomerName name;
    private String password;
    private Mail email;
    private Company company;

    public Customer() {}

    public Customer(CustomerId customerId, CustomerName name, String password, Mail email) {
        this.customerId = customerId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString(){
        return ("Customer { " +
                ", customer_id: " + this.customerId +
                ", Name: " + this.name +
                " }"
        );
    }

    @Override
    public boolean equals (Object obj){
        if(obj instanceof Customer customer) {
            return !(!customerId.equals(customer.getCustomerId()) ||
                    !this.name.equals(customer.getName())) ||
                    ! this.email.equals(customer.getEmail());
        }
        return false;
    }
}
