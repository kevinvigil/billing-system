package com.system.billingsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "Company")
@AllArgsConstructor
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;
    private String direction;
    private String phone;
    @Column(unique = true, nullable = false)
    private String cuit;
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "sellerCompany")
    private List<Invoice> soldInvoices;

    @OneToMany(mappedBy = "buyerCompany")
    private List<Invoice> purchasedInvoices;

    public Company() {}

    public Company(UUID id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return ("Company { " +
                ", id: " + this.id +
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

        if ( (!Objects.equals(id, company.id)) || (!Objects.equals(name, company.name)) || (!Objects.equals(direction, company.direction))
        || (!Objects.equals(phone, company.phone)) || (!Objects.equals(cuit, company.cuit)) ) return false;

        return Objects.equals(email, company.email);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
