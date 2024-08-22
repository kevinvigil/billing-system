package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.microtypes.Cuit;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.microtypesmapper.AddressMapper;
import com.system.billingsystem.entities.microtypes.microtypesmapper.PhoneMapper;
import com.system.billingsystem.entities.microtypes.names.CompanyName;
import jakarta.validation.constraints.NotNull;

public class CompanyDtoMapper {

    private CompanyDtoMapper() {}

    public static Company toDomain(@NotNull CompanyDto companyDto) {
        Company company = new Company();

        company.setCompanyId(new CompanyId(companyDto.companyId()));
        company.setName(new CompanyName(companyDto.name()));
        company.setCuit(new Cuit(companyDto.cuit()));
        company.setPhone(PhoneMapper.toDomain(companyDto.phone()));
        company.setEmail(companyDto.email());
        company.setAddress(AddressMapper.toDomain(companyDto.address()));

        company.setSoldInvoices(companyDto.soldInvoices());
        company.setPurchasedInvoices(companyDto.purchasedInvoices());

        return company;
    }

    public static CompanyDto toDto(@NotNull Company company) {
        return new CompanyDto(
            company.getCompanyId().getValue(),
            company.getName().getName(),
            company.getAddress().toString(),
            company.getPhone().toString(),
            company.getEmail(),
            company.getCuit().getCuit(),
            company.getSoldInvoices(),
            company.getPurchasedInvoices()
        );
    }
}
