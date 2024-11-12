package com.billingsystem.authservice.entity.microtypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private String firstName;
    private String secondName;
    private String surname;

    public Name(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return firstName +" " + secondName +" " + surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Name c) {
            return firstName.equals(c.getFirstName())
                    && secondName.equals(c.getSecondName())
                    && surname.equals(c.getSurname());
        }
        return false;
    }
}
