package com.system.billingSystem.dto;

import com.system.billingSystem.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
public class CompanyDto {

    private Long id;
    private String name;
    private String direction;
    private String phone;
    private String email;
    private String cuit;

    public CompanyDto(@NotNull Company c){
        this.id = c.getId();
        this.name = c.getName();
        this.cuit = c.getCuit();
        this.email = c.getEmail();
        this.phone = c.getPhone();
        this.direction = c.getDirection();
    }

}
