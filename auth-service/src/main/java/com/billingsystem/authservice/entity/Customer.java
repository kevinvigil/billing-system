package com.billingsystem.authservice.entity;


import com.billingsystem.authservice.entity.microtypes.Id;
import com.billingsystem.authservice.entity.microtypes.Mail;
import com.billingsystem.authservice.entity.microtypes.Name;
import com.billingsystem.authservice.entity.microtypes.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Id customerId;
    private Name username;
    private String password;
    private Mail email;
    private Time createdAt;
    private Time lastLogin;
    private boolean isActive;


    public Customer(Id customerId, Name name, String password, Mail email) {
        this.customerId = customerId;
        this.username = name;
        this.password = password;
        this.email = email;
    }
}
