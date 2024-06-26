package com.system.billingSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "Company")
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private String direction;
    private String phone;

    @Column(unique = true, nullable = false)
    private String cuit;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "company")
    private List<Invoice> invoice;

    public Company() {}

    public Company(Long id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return ("ID: " + this.id + " Name: " + this.name);
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
}
