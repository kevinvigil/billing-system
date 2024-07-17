package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Customer;

public class CustomerDtoMapper {

    private CustomerDtoMapper() {}

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getCompany().getId()
        );
    }

    public static Customer toDomain(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setId(customerDto.id());
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPassword(customer.getPassword());

        customer.setCompany(new Company(customerDto.company()));

        return customer;
    }
}
