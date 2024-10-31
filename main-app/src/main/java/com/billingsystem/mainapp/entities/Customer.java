package com.billingsystem.mainapp.entities;

import com.billingsystem.mainapp.entities.microtypes.Mail;
import com.billingsystem.mainapp.entities.microtypes.ids.CustomerId;
import com.billingsystem.mainapp.entities.microtypes.names.CustomerName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private CustomerId customerId;
    private CustomerName username;
    private String password;
    private Mail email;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private boolean isActive;
    private Company company;

    public Customer() {}

    public Customer(CustomerId customerId, CustomerName username, String password, Mail email) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Customer(CustomerId customerId, CustomerName username, String password, Mail email, Company company) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString(){
        return ("Customer { " +
                ", customer_id: " + this.customerId +
                ", Name: " + this.username +
                " }"
        );
    }

    @Override
    public boolean equals (Object obj){
        if(obj instanceof Customer customer) {
            return !(!customerId.equals(customer.getCustomerId()) ||
                    !this.username.equals(customer.getUsername())) ||
                    ! this.email.equals(customer.getEmail());
        }
        return false;
    }
}
