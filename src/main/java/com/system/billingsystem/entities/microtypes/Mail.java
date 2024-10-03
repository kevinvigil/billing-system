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

}
