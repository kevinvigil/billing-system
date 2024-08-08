package com.system.billingSystem.entities;

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
    
    private UUID company_id;
    private String cuit;
    private String direction;
    private String name;
    private String phone;
    
    @Email
    private String email;

    private List<Invoice> soldInvoices;

    private List<Invoice> purchasedInvoices;

    public Company() {}

    public Company(UUID company_id) {
        this.company_id = company_id;
    }


    @Override
    public String toString(){
        return ("Company { " +
                ", company_id: " + this.company_id +
                ", name: " + this.name +
                ", direction: " + this.direction +
                ", phone: " + this.phone +
                " }");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if ( (!Objects.equals(company_id, company.company_id)) || (!Objects.equals(name, company.name)) || (!Objects.equals(direction, company.direction))
        || (!Objects.equals(phone, company.phone)) || (!Objects.equals(cuit, company.cuit)) ) return false;

        return Objects.equals(email, company.email);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
