package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.entities.Company;
import jakarta.validation.constraints.NotNull;

public class CompanyDtoMapper {

    private CompanyDtoMapper() {}

    

    public static Company toDomain(@NotNull CompanyDto companyDto) {
        Company company = new Company();

        company.setCompanyId(companyDto.companyId());
        company.setName(companyDto.name());
        company.setCuit(companyDto.cuit());
        company.setPhone(companyDto.phone());
        company.setEmail(companyDto.email());
        company.setDirection(companyDto.direction());

        company.setSoldInvoices(companyDto.soldInvoices());
        company.setPurchasedInvoices(companyDto.purchasedInvoices());

        return company;
    }

    public static CompanyDto toDto(@NotNull Company company) {
        return new CompanyDto(
                company.getCompanyId(),
                company.getName(),
                company.getDirection(),
                company.getPhone(),
                company.getEmail(),
                company.getCuit(),
                company.getSoldInvoices(),
                company.getPurchasedInvoices()
        );
    }
}
