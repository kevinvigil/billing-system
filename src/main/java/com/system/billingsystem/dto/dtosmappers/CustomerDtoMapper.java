package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Customer;

public class CustomerDtoMapper {

    private CustomerDtoMapper() {}

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                (customer.getCompany() == null)? null: customer.getCompany().getCompanyId()
        );
    }

    public static Customer toDomain(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setCustomerId(customerDto.customerDto_id());
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPassword(customer.getPassword());

        customer.setCompany(new Company(customerDto.company()));

        return customer;
    }
}
