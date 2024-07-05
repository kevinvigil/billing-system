package com.system.billingSystem.dto;

import com.system.billingSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public record UserDto (
        Long id,
        String name,
        String email,
        Long company
) {

    public UserDto(Long id, String name, String email, Long company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.company = company;
    }

    public static UserDto newUserDto (@NotNull User u){
        return new UserDto(u.getId(), u.getName(), u.getEmail(), u.getCompany().getId());
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
    public String email() {
        return email;
    }

    @Override
    public Long company() {
        return company;
    }
}
