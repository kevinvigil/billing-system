package com.system.billingsystem.entities.microtypes;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Mail {

    @Email
    private String value;

    public Mail() {}
    public Mail(String email) {
        this.value = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return value.equals(mail.value);
    }
}
