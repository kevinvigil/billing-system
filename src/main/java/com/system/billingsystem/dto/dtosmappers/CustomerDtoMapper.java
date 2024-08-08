package com.system.billingSystem.dto.dtosmappers;

import com.system.billingSystem.dto.CustomerDto;
import com.system.billingSystem.entities.Company;
import com.system.billingSystem.entities.Customer;

public class CustomerDtoMapper {

    private CustomerDtoMapper() {}

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomer_id(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                (customer.getCompany() == null)? null: customer.getCompany().getCompany_id()
        );
    }

    public static Customer toDomain(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setCustomer_id(customerDto.customerDto_id());
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPassword(customer.getPassword());

        customer.setCompany(new Company(customerDto.company()));

        return customer;
    }
}
