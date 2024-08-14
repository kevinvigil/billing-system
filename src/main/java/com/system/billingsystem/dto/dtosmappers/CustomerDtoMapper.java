package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;

public class CustomerDtoMapper {

    private CustomerDtoMapper() {}

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId().getValue(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                (customer.getCompany() == null)? null: customer.getCompany().getCompanyId().getValue()
        );
    }

    public static Customer toDomain(CustomerDto customerDto) {
        Customer customer = new Customer();

        customer.setCustomerId(new CustomerId(customerDto.customerId()));
        customer.setName(customerDto.name());
        customer.setEmail(customerDto.email());
        customer.setPassword(customer.getPassword());

        customer.setCompany(new Company(new CompanyId(customerDto.company())));

        return customer;
    }
}
