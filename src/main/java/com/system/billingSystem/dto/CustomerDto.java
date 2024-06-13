package com.system.billingSystem.dto;

import com.system.billingSystem.model.Customer;
import org.jetbrains.annotations.NotNull;

public record CustomerDto (
        Long id,
        String name,
        String direction,
        String phone,
        String email) {

    public CustomerDto(Long id, String name, String direction, String phone, String email) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.phone = phone;
        this.email = email;
    }

    public CustomerDto(@NotNull Customer c){
        this(c.getId(), c.getName(), c.getDirection(), c.getEmail(), c.getPhone());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String direction() {
        return direction;
    }

    @Override
    public String phone() {
        return phone;
    }

    @Override
    public String email() {
        return email;
    }
}
