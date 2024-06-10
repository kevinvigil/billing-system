package com.system.billingSystem.dto;

import com.system.billingSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Long company;

    public UserDto (@NotNull User u){
        this.id = u.getId();
        this.name = u.getName();
        this.company = u.getCompany().getId();
        this.email = u.getEmail();
    }
}
