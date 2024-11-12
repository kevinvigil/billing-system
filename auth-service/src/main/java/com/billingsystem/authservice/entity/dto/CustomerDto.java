package com.billingsystem.authservice.entity.dto;


import java.util.UUID;

public record CustomerDto (
        UUID customerId,
        String username,
        String email,
        String password
){

    public CustomerDto(UUID customerId, String username, String email, String password) {
        this.customerId = customerId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID customerId() {
        return customerId;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomerDto other) {
            return (!(!other.customerId().equals(this.customerId()) ||
                    !other.username().equals(this.username()) ||
                    !other.email().equals(this.email())));
        }
        return false;
    }

}
