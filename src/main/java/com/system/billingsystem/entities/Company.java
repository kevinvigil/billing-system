package com.system.billingsystem.entities;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Company {

    private UUID companyId;
    private String cuit;
    private String address;
    private String name;
    private String phone;

    @Email
    private String email;

    private List<Invoice> soldInvoices;

    private List<Invoice> purchasedInvoices;

    public Company() {}

    public Company(UUID companyId) {
        this.companyId = companyId;
    }


    @Override
    public String toString(){
        return ("Company { " +
                ", companyId: " + this.companyId +
                ", name: " + this.name +
                ", address: " + this.address +
                ", phone: " + this.phone +
                " }");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if ( (!Objects.equals(companyId, company.companyId)) || (!Objects.equals(name, company.name)) || (!Objects.equals(address, company.address))
        || (!Objects.equals(phone, company.phone)) || (!Objects.equals(cuit, company.cuit)) ) return false;

        return Objects.equals(email, company.email);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
