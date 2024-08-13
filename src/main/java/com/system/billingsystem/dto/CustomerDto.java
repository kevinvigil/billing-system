package com.system.billingsystem.dto;

import java.util.UUID;

public record CustomerDto(
        UUID customerDto_id,
        String name,
        String email,
        String password,
        UUID company
) {

    public CustomerDto(UUID customerDto_id, String name, String email, String password, UUID company) {
        this.customerDto_id = customerDto_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = company;
    }

    @Override
    public UUID customerDto_id() {
        return customerDto_id;
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
