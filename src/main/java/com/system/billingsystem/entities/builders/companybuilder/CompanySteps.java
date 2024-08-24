package com.system.billingsystem.entities.builders.companybuilder;

import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.microtypes.Address;
import com.system.billingsystem.entities.microtypes.Cuit;
import com.system.billingsystem.entities.microtypes.Phone;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.names.CompanyName;

public class CompanySteps implements CompanyIdSteps, CompanyNameStep, CompanyCuitStep, CompanyAddressStep, CompanyPhoneStep, CompanyEmailStep, CompanyBuildStep {
    private CompanyId companyId;
    private Cuit cuit;
    private Address address;
    private CompanyName name;
    private Phone phone;
    private String email;

    @Override
    public Company build() {
        return new Company(companyId, email, phone, name, address, cuit);
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
    public CompanyBuildStep email(String email) {
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
}
