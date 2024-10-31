package com.billingsystem.mainapp.dto.dtosmappers;

import com.billingsystem.mainapp.dto.CompanyDto;
import com.billingsystem.mainapp.dto.CustomerDto;
import com.billingsystem.mainapp.entities.Company;
import com.billingsystem.mainapp.entities.Customer;
import com.billingsystem.mainapp.entities.microtypes.Mail;
import com.billingsystem.mainapp.entities.microtypes.ids.CustomerId;
import com.billingsystem.mainapp.entities.microtypes.names.CustomerName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static com.billingsystem.mainapp.dto.dtosmappers.CompanyMapper.COMPANY_MAPPER;

@Mapper
public interface CustomerMapper {

    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(target = "customerId", expression = "java(customer.getCustomerId().getValue())"),
            @Mapping(target = "username", expression = "java(customer.getUsername().toString())"),
            @Mapping(target = "password", expression = "java(customer.getPassword())"),
            @Mapping(target = "email", expression = "java(customer.getEmail().getValue())"),
            @Mapping(target = "company", expression = "java(mapToCompanyDto(customer.getCompany()))")
    })
    CustomerDto toDto(Customer customer);

    @Mappings({
            @Mapping(target = "customerId", expression = "java(mapCustomerId(dto.customerId()))"),
            @Mapping(target = "username", expression = "java(mapCustomerName(dto.username()))"),
            @Mapping(target = "password", expression = "java(dto.password())"),
            @Mapping(target = "email", expression = "java(mapEmailToDomain(dto.email()))"),
            @Mapping(target = "company", expression = "java(mapToCompany(dto.company()))")

    })
    Customer toDomain(CustomerDto dto);

    default Mail mapEmailToDomain(String email){
        if (email == null) return null;
        return new Mail(email);
    }

    default CustomerId mapCustomerId(UUID customerId){
        if (customerId == null) return null;
        return new CustomerId(customerId);
    }

    default CustomerName mapCustomerName(String name){
        String [] array = name.split(" ");
        return switch (array.length) {
            case 3 -> new CustomerName(array[0], array[1], array[2]);
            case 2 -> new CustomerName(array[0], array[1]);
            default -> new CustomerName();
        };
    }

    default CompanyDto mapToCompanyDto(Company company){
        if (company == null) return null;
        return COMPANY_MAPPER.toDto(company);
    }

    default Company mapToCompany(CompanyDto companyDto){
        if (companyDto == null) return null;
        return COMPANY_MAPPER.toDomain(companyDto);
    }
}
