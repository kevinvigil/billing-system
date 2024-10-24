package com.system.billingsystem.entities;

import com.system.billingsystem.entities.microtypes.Mail;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.entities.microtypes.names.CustomerName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class Customer {

    private CustomerId customerId;
    private CustomerName userName;
    private String password;
    private Mail email;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private boolean isActive;
    private Company company;

    public Customer() {}

    public Customer(CustomerId customerId, CustomerName userName, String password, Mail email) {
        this.customerId = customerId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString(){
        return ("Customer { " +
                ", customer_id: " + this.customerId +
                ", Name: " + this.userName +
                " }"
        );
    }

    @Override
    public boolean equals (Object obj){
        if(obj instanceof Customer customer) {
            return !(!customerId.equals(customer.getCustomerId()) ||
                    !this.userName.equals(customer.getUserName())) ||
                    ! this.email.equals(customer.getEmail());
        }
        return false;
    }
}
