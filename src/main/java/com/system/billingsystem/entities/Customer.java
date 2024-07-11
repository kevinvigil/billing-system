package com.system.billingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "Customer")
public class Customer {

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

    public Customer() {}

    @Override
    public String toString(){
        return ("Customer { " +
                ", id: " + this.id +
                ", Name: " + this.name +
                " }"
        );
    }

}
