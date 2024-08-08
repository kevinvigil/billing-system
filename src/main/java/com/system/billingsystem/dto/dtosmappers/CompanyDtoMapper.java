package com.system.billingSystem.dto.dtosmappers;

import com.system.billingSystem.dto.CompanyDto;
import com.system.billingSystem.entities.Company;
import jakarta.validation.constraints.NotNull;

public class CompanyDtoMapper {

    private CompanyDtoMapper() {}



    public static Company toDomain(@NotNull CompanyDto companyDto) {
        Company company = new Company();

        company.setCompanyId(companyDto.CompanyDto_id());
        company.setName(companyDto.name());
        company.setCuit(companyDto.cuit());
        company.setPhone(companyDto.phone());
        company.setEmail(companyDto.email());
        company.setAddress(companyDto.direction());

        company.setSoldInvoices(companyDto.soldInvoices());
        company.setPurchasedInvoices(companyDto.purchasedInvoices());

        return company;
    }

    public static CompanyDto toDto(@NotNull Company company) {
        return new CompanyDto(
                company.getCompanyId(),
                company.getName(),
                company.getAddress(),
                company.getPhone(),
                company.getEmail(),
                company.getCuit(),
                company.getSoldInvoices(),
                company.getPurchasedInvoices()
        );
    }
}
