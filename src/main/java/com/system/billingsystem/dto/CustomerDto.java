package com.system.billingsystem.dto;

import java.util.UUID;

public record CustomerDto(
        UUID customerId,
        String name,
        String email,
        String password,
        UUID company
) {

    public CustomerDto(UUID customerId, String name, String email, String password, UUID company) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = company;
    }

    public UUID customerId() {
        return customerId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public UUID company() {
        return company;
    }
}
