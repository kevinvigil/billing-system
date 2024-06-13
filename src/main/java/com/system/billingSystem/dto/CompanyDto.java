package com.system.billingSystem.dto;

import com.system.billingSystem.model.Company;
import org.jetbrains.annotations.NotNull;

public record CompanyDto(
        Long id,
        String name,
        String direction,
        String phone,
        String email,
        String cuit ) {

    public CompanyDto(Long id, String name, String direction, String phone, String email, String cuit) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.phone = phone;
        this.email = email;
        this.cuit = cuit;
    }

    public static CompanyDto newCompanyDto(@NotNull Company c){
        return new CompanyDto(c.getId(), c.getName(), c.getCuit(), c.getEmail(), c.getPhone(), c.getDirection());
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
