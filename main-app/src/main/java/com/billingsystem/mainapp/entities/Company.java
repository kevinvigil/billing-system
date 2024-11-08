package com.billingsystem.mainapp.entities;

import com.billingsystem.mainapp.entities.microtypes.Address;
import com.billingsystem.mainapp.entities.microtypes.Cuit;
import com.billingsystem.mainapp.entities.microtypes.Mail;
import com.billingsystem.mainapp.entities.microtypes.Phone;
import com.billingsystem.mainapp.entities.microtypes.ids.CompanyId;
import com.billingsystem.mainapp.entities.microtypes.ids.CustomerId;
import com.billingsystem.mainapp.entities.microtypes.names.CompanyName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Company {
    
    private CompanyId companyId;
    private Cuit cuit;
    private Address address;
    private CompanyName name;
    private Phone phone;
    private Mail email;
    private CustomerId customerId;
    private List<Invoice> soldInvoices;
    private List<Invoice> purchasedInvoices;

    public Company() {}

    public Company(CompanyId companyId) {
        this.companyId = companyId;
    }

    public Company(CompanyId companyId, Mail email, Phone phone,
                   CompanyName name, Address address, Cuit cuit) {
        this.companyId = companyId;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.cuit = cuit;
    }

    public Company(CompanyId companyId, Cuit cuit, Address address, CompanyName name, Phone phone, Mail email, List<Invoice> soldInvoices, List<Invoice> purchasedInvoices) {
    }

    @Override
    public String toString(){
        return ("Company { " +
                ", \n company_id: " + this.companyId +
                ", \n name: " + this.name +
                ", \n address: " + this.address +
                ", \n phone: " + this.phone +
                " \n }");
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
