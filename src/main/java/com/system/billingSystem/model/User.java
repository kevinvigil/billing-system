package com.system.billingSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private String email;
    private String password;

    @ManyToOne
    private Company company;

    public User() {

    }

    @Override
    public String toString(){
        return ("ID: " + this.id + " Name: " + this.name);
    }

}
