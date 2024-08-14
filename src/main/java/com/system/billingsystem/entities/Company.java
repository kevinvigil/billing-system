package com.system.billingsystem.entities;

import com.system.billingsystem.entities.microtypes.ids.CompanyId;
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
    
    private CompanyId companyId;

    private String cuit;
    private String address;
    private String name;
    private String phone;

    public Company(CompanyId companyId, String email, String phone,
                   String name, String address, String cuit) {
        this.companyId = companyId;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.cuit = cuit;
    }

    @Email
    private String email;

    private List<Invoice> soldInvoices;

    private List<Invoice> purchasedInvoices;

    public Company() {}

    public Company(CompanyId companyId) {
        this.companyId = companyId;
    }


    @Override
    public String toString(){
        return ("Company { " +
                ", company_id: " + this.companyId +
                ", name: " + this.name +
                ", direction: " + this.address +
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
