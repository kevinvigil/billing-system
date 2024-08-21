package com.system.billingsystem.entities.microtypes.names;

import lombok.Data;

@Data
public class CustomerName {

    private String firstName;
    private String secondName;
    private String surname;

    public CustomerName(String firstName, String secondName, String surname) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.surname = surname;
    }

    public CustomerName(){}

    @Override
    public String toString() {
        return "{firstName: " + firstName +", secondName: " + secondName +", surname: " + surname +'}';
    }
}
