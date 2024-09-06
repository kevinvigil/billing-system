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

    public CustomerName(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public CustomerName(){}

    @Override
    public String toString() {
        return firstName +" " + secondName +" " + surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomerName c) {
            return firstName.equals(c.getFirstName())
                    && secondName.equals(c.getSecondName())
                    && surname.equals(c.getSurname());
        }
        return false;
    }
}
