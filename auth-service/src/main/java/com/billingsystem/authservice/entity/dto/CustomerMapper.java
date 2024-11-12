package com.billingsystem.authservice.entity.dto;

import com.billingsystem.authservice.entity.Customer;
import com.billingsystem.authservice.entity.microtypes.Id;
import com.billingsystem.authservice.entity.microtypes.Mail;
import com.billingsystem.authservice.entity.microtypes.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "customerId", expression = "java(customer.getCustomerId().getValue())"),
            @Mapping(target = "username", expression = "java(customer.getUsername().toString())"),
            @Mapping(target = "password", expression = "java(customer.getPassword())"),
            @Mapping(target = "email", expression = "java(customer.getEmail().getValue())")
    })
    CustomerDto toDto(Customer customer);

    @Mappings({
            @Mapping(target = "customerId", expression = "java(mapCustomerId(dto.customerId()))"),
            @Mapping(target = "username", expression = "java(mapCustomerName(dto.username()))"),
            @Mapping(target = "password", expression = "java(dto.password())"),
            @Mapping(target = "email", expression = "java(mapEmailToDomain(dto.email()))")

    })
    Customer toDomain(CustomerDto dto);

    default Mail mapEmailToDomain(String email){
        if (email == null) return null;
        return new Mail(email);
    }

    default Id mapCustomerId(UUID customerId){
        if (customerId == null) return null;
        return new Id(customerId);
    }

    default Name mapCustomerName(String name){
        String [] array = name.split(" ");
        return switch (array.length) {
            case 3 -> new Name(array[0], array[1], array[2]);
            case 2 -> new Name(array[0], array[1]);
            default -> new Name();
        };
    }
}

