package com.system.billingSystem.dto;

import com.system.billingSystem.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String direction;
    private String phone;
    private String email;

    public CustomerDto (@NotNull Customer c){
        this.id = c.getId();
        this.name = c.getName();
        this.direction = c.getDirection();
        this.email = c.getEmail();
        this.phone = c.getPhone();
    }
}
