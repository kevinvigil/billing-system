package com.system.billingSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "´User´")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne
    private Company company;

    public User() {}

    @Override
    public String toString(){
        return ("ID: " + this.id + " Name: " + this.name);
    }

}
