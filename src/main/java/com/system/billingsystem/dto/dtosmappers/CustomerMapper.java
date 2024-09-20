package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.entities.microtypes.Mail;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.entities.microtypes.names.CustomerName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.CompanyMapper.COMPANY_MAPPER;

@Mapper
public interface CustomerMapper {

    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(target = "customerId", expression = "java(customer.getCustomerId().getValue())"),
            @Mapping(target = "name", expression = "java(customer.getName().toString())"),
            @Mapping(target = "password", expression = "java(customer.getPassword())"),
            @Mapping(target = "email", expression = "java(customer.getEmail().getValue())"),
            @Mapping(target = "company", expression = "java(mapToCompanyDto(customer.getCompany()))")
    })
    CustomerDto toDto(Customer customer);

    @Mappings({
            @Mapping(target = "customerId", expression = "java(mapCustomerId(dto.customerId()))"),
            @Mapping(target = "name", expression = "java(mapCustomerName(dto.name()))"),
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
