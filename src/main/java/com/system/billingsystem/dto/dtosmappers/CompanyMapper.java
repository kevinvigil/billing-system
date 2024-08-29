package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.microtypes.*;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.names.CompanyName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface CompanyMapper {

    CompanyMapper COMPANY_MAPPER = Mappers.getMapper(CompanyMapper.class);

    @Mappings({
            @Mapping(target = "address", expression = "java(company.getAddress().toString())"),
            @Mapping(target = "phone", expression = "java(company.getPhone().toString())"),
            @Mapping(target = "companyId", expression = "java(company.getCompanyId().getValue())"),
            @Mapping(target = "name", expression = "java(company.getName().getName())"),
            @Mapping(target = "email", expression = "java(company.getEmail())"),
            @Mapping(target = "cuit", expression = "java(company.getCuit().getCuit())")
    })
    CompanyDto toDto(Company company);

    @Mappings({
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "companyId", expression = "java(mapCompanyId(dto.companyId()))"),
            @Mapping(target = "phone", expression = "java(mapPhone(dto.phone()))"),
            @Mapping(target = "name", expression = "java(mapCompanyName(dto.name()))"),
            @Mapping(target = "address", expression = "java(mapAddress(dto.address()))"),
            @Mapping(target = "cuit", expression = "java(mapCuit(dto.cuit()))")
    })
    Company toDomain(CompanyDto dto);

    default Address mapAddress(String address) {
        String[] parts = address.split(", ");
        if (parts.length == 4) {
            return new Address(parts[0], parts[2], parts[1], parts[3]);
        } else if (parts.length == 3) {
            return new Address(parts[0], parts[2], parts[1]);
        }
        return new Address();
    }

    default Phone mapPhone(String phone) {
        String[] parts = phone.split(" ");
        if (parts.length == 3) {
            return new Phone(parts[0], parts[1], parts[2]);
        }
        return new Phone();
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
