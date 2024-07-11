package com.system.billingsystem.DTOs;

import com.system.billingsystem.entities.Customer;
import org.jetbrains.annotations.NotNull;

public record CustomerDto(
        Long id,
        String name,
        String email,
        Long company
) {

    public CustomerDto(Long id, String name, String email, Long company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public CustomerDto (@NotNull Customer u){
        this (u.getId(), u.getName(), u.getEmail(), u.getCompany().getId());
    }

    @Override
    public Long id() {
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
    public Long company() {
        return company;
    }
}
