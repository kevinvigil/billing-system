package com.system.billingsystem.dto;

import com.system.billingsystem.entities.Invoice;
import org.apache.maven.model.Build;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record CompanyDto(
        UUID companyId,
        String name,
        String address,
        String phone,
        String email,
        String cuit,
        List<InvoiceDto> soldInvoices,
        List<InvoiceDto> purchasedInvoices ) {

    public CompanyDto(UUID companyId, String name, String address, String phone, String email, String cuit, List<InvoiceDto> soldInvoices, List<InvoiceDto> purchasedInvoices) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.cuit = cuit;
        this.soldInvoices = soldInvoices;
        this.purchasedInvoices = purchasedInvoices;
    }

    public  CompanyDto (UUID CompanyDto_id) {
        this (CompanyDto_id, null, null, null, null, null, null, null);
    }

    public UUID companyId() {
        return companyId;
    }

    @Override
    public String name() {
        return name;
    }

    public String address() {
        return address;
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
