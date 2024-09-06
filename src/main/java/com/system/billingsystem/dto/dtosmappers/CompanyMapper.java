package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.microtypes.*;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.names.CompanyName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.InvoiceMapper.INVOICE_MAPPER;

@Mapper
public interface CompanyMapper {

    CompanyMapper COMPANY_MAPPER = Mappers.getMapper(CompanyMapper.class);

    @Mappings({
            @Mapping(target = "address", expression = "java(company.getAddress().toString())"),
            @Mapping(target = "phone", expression = "java(company.getPhone().toString())"),
            @Mapping(target = "companyId", expression = "java(company.getCompanyId().getValue())"),
            @Mapping(target = "name", expression = "java(company.getName().getName())"),
            @Mapping(target = "email", expression = "java(company.getEmail())"),
            @Mapping(target = "cuit", expression = "java(company.getCuit().getCuit())"),
            @Mapping(target = "soldInvoices", expression = "java(mapInvoicesToDto(company.getSoldInvoices()))"),
            @Mapping(target = "purchasedInvoices", expression = "java(mapInvoicesToDto(company.getPurchasedInvoices()))")
    })
    CompanyDto toDto(Company company);

    default List<InvoiceDto> mapInvoicesToDto(List<Invoice> invoices){
        if (invoices == null) return new ArrayList<>();
        return invoices.stream().map(INVOICE_MAPPER::toDto).toList();
    }

    @Mappings({
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "companyId", expression = "java(mapCompanyId(dto.companyId()))"),
            @Mapping(target = "phone", expression = "java(mapPhone(dto.phone()))"),
            @Mapping(target = "name", expression = "java(mapCompanyName(dto.name()))"),
            @Mapping(target = "address", expression = "java(mapAddress(dto.address()))"),
            @Mapping(target = "cuit", expression = "java(mapCuit(dto.cuit()))"),
            @Mapping(target = "soldInvoices", expression = "java(mapInvoicesToDomain(dto.soldInvoices()))"),
            @Mapping(target = "purchasedInvoices", expression = "java(mapInvoicesToDomain(dto.purchasedInvoices()))")
    })
    Company toDomain(CompanyDto dto);

    default List<Invoice> mapInvoicesToDomain(List<InvoiceDto> dtoList){
        if (dtoList == null ) return new ArrayList<>();
        return dtoList.stream().map(INVOICE_MAPPER::toDomain).toList();
    }

    default Address mapAddress(String address) {
        String[] parts = address.split(", ");
        return switch (parts.length) {
            case 4 -> new Address(parts[0], parts[1], parts[2], parts[3]);
            case 3 -> new Address(parts[0], parts[1], parts[2]);
            default -> new Address();
        };
    }

    default Phone mapPhone(String phone) {
        String[] parts = phone.split(" ");
        return switch (parts.length) {
            case 3 -> new Phone(parts[0], parts[1], parts[2]);
            case 2 -> new Phone(parts[0], parts[1]);
            default -> new Phone();
        };
    }

    default CompanyId mapCompanyId(UUID companyId) {
        return new CompanyId(companyId);
    }

    default CompanyName mapCompanyName(String name) {
        return new CompanyName(name);
    }

    default Cuit mapCuit(String cuit) {
        return new Cuit(cuit);
    }
}
