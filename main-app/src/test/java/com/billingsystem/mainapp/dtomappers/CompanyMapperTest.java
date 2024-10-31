package com.billingsystem.mainapp.dtomappers;

import com.billingsystem.mainapp.dto.CompanyDto;
import com.billingsystem.mainapp.dto.InvoiceDto;
import com.billingsystem.mainapp.entities.Company;
import com.billingsystem.mainapp.entities.Invoice;
import com.billingsystem.mainapp.entities.InvoiceCategory;
import com.billingsystem.mainapp.entities.InvoiceVoucher;
import com.billingsystem.mainapp.entities.builders.companybuilder.CompanyBuilder;
import com.billingsystem.mainapp.entities.builders.invoicebuilder.InvoiceBuilder;
import com.billingsystem.mainapp.entities.microtypes.*;
import com.billingsystem.mainapp.entities.microtypes.ids.CompanyId;
import com.billingsystem.mainapp.entities.microtypes.ids.InvoiceId;
import com.billingsystem.mainapp.entities.microtypes.names.CompanyName;
import com.billingsystem.mainapp.entities.microtypes.prices.InvoicePrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.billingsystem.mainapp.dto.dtosmappers.CompanyMapper.COMPANY_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompanyMapperTest {

    private Company company;
    private CompanyDto companyDto;

    @BeforeEach
    public void setUp(){
        // Given
        UUID baseId = UUID.randomUUID();
        company = CompanyBuilder.newBuilder()
                .companyId(new CompanyId(baseId))
                .name(new CompanyName("company"))
                .cuit(new Cuit("cuit"))
                .address(new Address("country","state","city", "zip"))
                .phone(new Phone("+53","2222","2222"))
                .email(new Mail("newEmail@company.com"))
                .soldInvoices(null)
                .purchasedInvoices(null)
                .build();

        companyDto = new CompanyDto(
                baseId,
                "company",
                "country, state, city, zip",
                "+53 2222 2222",
                "newEmail@company.com",
                "cuit",
                null,
                null);
    }

    @Test
    public void shouldMapCompanyToDto() {
        // Then
        CompanyDto newCompanyDto = COMPANY_MAPPER.toDto(company);
        System.out.println(companyDto);

        // When
        assertEquals(companyDto.companyId(), newCompanyDto.companyId());
        assertEquals(companyDto.name(), newCompanyDto.name());
        assertEquals(companyDto.cuit(), newCompanyDto.cuit());
        assertEquals(companyDto.address(), newCompanyDto.address());
        assertEquals(companyDto.phone(), newCompanyDto.phone());
        assertEquals(companyDto.email(), newCompanyDto.email());
    }

    @Test
    public void shouldMapDtoToCompany() {
        // Then
        Company newCompany = COMPANY_MAPPER.toDomain(companyDto);

        // When
        assertEquals(company.getCompanyId().getValue(), newCompany.getCompanyId().getValue());
        assertEquals(company.getName().toString(), newCompany.getName().toString());
        assertEquals(company.getCuit().toString(), newCompany.getCuit().toString());
        assertEquals(company.getAddress().toString(), newCompany.getAddress().toString());
        assertEquals(company.getPhone().toString(), newCompany.getPhone().toString());
        assertEquals(company.getEmail(), newCompany.getEmail());
    }

    @Test
    public void shouldMapCompanyToCompanyDtoWhitList(){
        // Given
        Invoice invoice = InvoiceBuilder.newBuilder()
                .invoiceId(new InvoiceId(UUID.randomUUID()))
                .date(new Timestamp(111111))
                .paid(false)
                .invoiced(false)
                .price(new InvoicePrice(new BigDecimal(10)))
                .discount(new Discount(0))
                .invoiceVoucher(InvoiceVoucher.CASH)
                .category(InvoiceCategory.A)
                .sellerCompany(null)
                .buyerCompany(null)
                .ListInvoiceProducts(null)
                .build();

        Invoice invoice1 = InvoiceBuilder.newBuilder()
                .invoiceId(new InvoiceId(UUID.randomUUID()))
                .date(new Timestamp(111111))
                .paid(false)
                .invoiced(false)
                .price(new InvoicePrice(new BigDecimal(100)))
                .discount(new Discount(10))
                .invoiceVoucher(InvoiceVoucher.REFERENCE)
                .category(InvoiceCategory.B)
                .sellerCompany(null)
                .buyerCompany(null)
                .ListInvoiceProducts(null)
                .build();

        List<Invoice> invoices = new ArrayList<>();

        // Then
        invoices.add(invoice);
        invoices.add(invoice1);

        company.setSoldInvoices(invoices);

        // When
        CompanyDto newCompanyDto = COMPANY_MAPPER.toDto(company);
        System.out.print(newCompanyDto);

        assertEquals(companyDto.companyId(), newCompanyDto.companyId());
        assertNotNull(newCompanyDto.soldInvoices());
        assertEquals(2, newCompanyDto.soldInvoices().size());
    }

    @Test
    public void shouldMapCompanyDtoToCompanyWhitList(){
        // Given
        InvoiceDto invoiceDto = new InvoiceDto(
                UUID.randomUUID(),
                new Timestamp(111111),
                false,
                false,
                new BigDecimal(10),
                "ARS",
                0,
                "CASH",
                "A",
                null, null, null
        );

        InvoiceDto invoiceDto1 = new InvoiceDto(
                UUID.randomUUID(),
                new Timestamp(111111),
                false,
                false,
                new BigDecimal(10),
                "ARS",
                0,
                "CASH",
                "A",
                null, null, null
        );

        List<InvoiceDto> invoices = new ArrayList<>();

        // Then
        invoices.add(invoiceDto);
        invoices.add(invoiceDto1);

        companyDto = new CompanyDto(
                UUID.randomUUID(),
                "company",
                "country, state, city, zip",
                "+53 2222 2222",
                "newEmail@company.com",
                "cuit",
                null,
                invoices);

        // When
        Company newCompany = COMPANY_MAPPER.toDomain(companyDto);
        System.out.print(newCompany);

        assertEquals(companyDto.companyId(), newCompany.getCompanyId().getValue());
        assertNotNull(newCompany.getPurchasedInvoices());
        assertTrue(newCompany.getPurchasedInvoices().size()>1);
    }
}
