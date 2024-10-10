package com.system.billingsystem.dto;

import lombok.EqualsAndHashCode;

import java.util.UUID;

public record CustomerDto(
        UUID customerId,
        String name,
        String email,
        String password,
        CompanyDto company
) {

    public CustomerDto(UUID customerId, String name, String email, String password, CompanyDto company) {
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
    public CompanyDto company() {
        return company;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomerDto other) {
            return (!(!other.customerId().equals(this.customerId()) ||
                    !other.name().equals(this.name()) ||
                    !other.email().equals(this.email())));
        }
        return false;


    }
}
