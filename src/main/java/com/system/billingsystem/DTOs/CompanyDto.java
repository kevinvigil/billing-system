package com.system.billingsystem.DTOs;

import com.system.billingsystem.entities.Company;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record CompanyDto(
        UUID id,
        String name,
        String direction,
        String phone,
        String email,
        String cuit ) {

    public CompanyDto(UUID id, String name, String direction, String phone, String email, String cuit) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.phone = phone;
        this.email = email;
        this.cuit = cuit;
    }

    public  CompanyDto (UUID id) {
        this (id, null, null, null, null, null);
    }

    public CompanyDto (@NotNull Company c){
        this (c.getId(), c.getName(), c.getCuit(), c.getEmail(), c.getPhone(), c.getDirection());
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
    public String direction() {
        return direction;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String cuit() {
        return cuit;
    }
}
