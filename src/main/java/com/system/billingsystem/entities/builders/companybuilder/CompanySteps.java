package com.system.billingsystem.entities.builders.companybuilder;

import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.microtypes.Address;
import com.system.billingsystem.entities.microtypes.Cuit;
import com.system.billingsystem.entities.microtypes.Phone;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.names.CompanyName;

import java.util.List;

public class CompanySteps implements CompanyIdSteps, CompanyNameStep, CompanyCuitStep, CompanyAddressStep,
        CompanyPhoneStep, CompanyEmailStep, CompanyBuildStep, CompanySoldInvoicesStep, CompanyPurchasedInvoicesStep {
    private CompanyId companyId;
    private Cuit cuit;
    private Address address;
    private CompanyName name;
    private Phone phone;
    private String email;
    private List<Invoice> soldInvoices;
    private List<Invoice> purchasedInvoices;

    @Override
    public Company build() {
        return new Company(companyId, cuit, address, name, phone, email, soldInvoices, purchasedInvoices);
    }
    @Override
    public CompanyPhoneStep address(Address address) {
        this.address = address;
        return this;
    }
    @Override
    public CompanyAddressStep cuit(Cuit cuit) {
        this.cuit = cuit;
        return this;
    }
    @Override
    public CompanySoldInvoicesStep email(String email) {
        this.email = email;
        return this;
    }
    @Override
    public CompanyCuitStep name(CompanyName name) {
        this.name = name;
        return this;
    }
    @Override
    public CompanyEmailStep phone(Phone phone) {
        this.phone = phone;
        return this;
    }
    @Override
    public CompanyNameStep companyId(CompanyId companyId) {
        this.companyId = companyId;
        return this;
    }

    @Override
    public CompanyBuildStep purchasedInvoices(List<Invoice> invoices) {
        this.purchasedInvoices = invoices;
        return this;
    }

    @Override
    public CompanyPurchasedInvoicesStep soldInvoices(List<Invoice> invoices) {
        this.soldInvoices = invoices;
        return this;
    }
}
