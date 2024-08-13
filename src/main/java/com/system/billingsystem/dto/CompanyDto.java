package com.system.billingsystem.dto;

import com.system.billingsystem.entities.Invoice;

import java.util.List;
import java.util.UUID;

public record CompanyDto(
        UUID CompanyDto_id,
        String name,
        String direction,
        String phone,
        String email,
        String cuit,
        List<Invoice> soldInvoices,
        List<Invoice> purchasedInvoices ) {

    public CompanyDto(UUID CompanyDto_id, String name, String direction, String phone, String email, String cuit, List<Invoice> soldInvoices, List<Invoice> purchasedInvoices) {
        this.CompanyDto_id = CompanyDto_id;
        this.name = name;
        this.direction = direction;
        this.phone = phone;
        this.email = email;
        this.cuit = cuit;
        this.soldInvoices = soldInvoices;
        this.purchasedInvoices = purchasedInvoices;
    }

    public  CompanyDto (UUID CompanyDto_id) {
        this (CompanyDto_id, null, null, null, null, null, null, null);
    }

    @Override
    public UUID CompanyDto_id() {
        return CompanyDto_id;
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
