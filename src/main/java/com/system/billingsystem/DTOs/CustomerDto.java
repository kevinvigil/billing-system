package com.system.billingsystem.DTOs;

import com.system.billingsystem.entities.Customer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record CustomerDto(
        UUID id,
        String name,
        String email,
        UUID company
) {

    public CustomerDto(UUID id, String name, String email, UUID company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public CustomerDto (@NotNull Customer u){
        this (u.getId(), u.getName(), u.getEmail(), u.getCompany().getId());
    }

    @Override
    public UUID id() {
        return id;
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
