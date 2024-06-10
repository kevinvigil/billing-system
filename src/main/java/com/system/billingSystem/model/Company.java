package com.system.billingSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

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
    private String email;
    private String cuit;

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

}
