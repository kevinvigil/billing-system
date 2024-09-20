package com.system.billingsystem.dtomappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.entities.builders.companybuilder.CompanyBuilder;
import com.system.billingsystem.entities.builders.customerbuilder.CustomerBuilder;
import com.system.billingsystem.entities.microtypes.Address;
import com.system.billingsystem.entities.microtypes.Cuit;
import com.system.billingsystem.entities.microtypes.Mail;
import com.system.billingsystem.entities.microtypes.Phone;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.entities.microtypes.names.CompanyName;
import com.system.billingsystem.entities.microtypes.names.CustomerName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.CustomerMapper.CUSTOMER_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerMapperTest {

    private Customer customer;
    private CustomerDto customerDto;

    private static Company company;

    @BeforeAll
    public static void setUpBeforeClass(){
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
    }

    @BeforeEach
    public void setUp() {
        // Given
        UUID baseId = UUID.randomUUID();

        customer = CustomerBuilder.newBuilder()
                .CustomerId(new CustomerId(baseId))
                .name(new CustomerName("first","second","surname"))
                .password("password")
                .email(new Mail("customer@customer.com"))
                .company(null)
                .build();

        customerDto = new CustomerDto(
                baseId,
                "first second surname",
                "customer@customer.com",
                "password",
                null
        );
    }

    @Test
    public void shouldMapCustomerToDto() {
        //Then
        CustomerDto newCustomerDto = CUSTOMER_MAPPER.toDto(customer);

        //When
        assertEquals(customerDto, newCustomerDto);
    }

    @Test
    public void shouldMapDtoToCustomer() {
        // Then
        Customer newCustomer = CUSTOMER_MAPPER.toDomain(customerDto);
        // When
        assertEquals(customer, newCustomer);
    }

    @Test
    public void shouldMapCustomerToDtoWhitCompany() {
        // Given
        customer.setCompany(company);

        // Then
        CustomerDto newCustomerDto = CUSTOMER_MAPPER.toDto(customer);

        // When
        assertNotNull(newCustomerDto);
        assertNotNull(newCustomerDto.customerId());
        assertEquals(customer.getCustomerId().getValue(), newCustomerDto.customerId());
        assertNotNull(newCustomerDto.company());
        assertEquals(company.getCompanyId().getValue(), newCustomerDto.company().companyId());
    }

    @Test
    public void shouldMapDtoToCustomerWhitCompany() {
        // Given
        CompanyDto companyDto = new CompanyDto(
                company.getCompanyId().getValue(),
                "company",
                "country, state, city, zip",
                "+53 2222 2222",
                "newEmail@company.com",
                "cuit",
                null,
                null);

        customerDto = new CustomerDto(
                customer.getCustomerId().getValue(),
                "first second surname",
                "customer@customer.com",
                "password",
                companyDto
        );

        // Then
        Customer newCustomer = CUSTOMER_MAPPER.toDomain(customerDto);

        // When
        assertNotNull(newCustomer);
        assertNotNull(newCustomer.getCustomerId());
        assertEquals(customer.getCustomerId().getValue(), newCustomer.getCustomerId().getValue());
        assertNotNull(newCustomer.getCompany());
        assertEquals(company.getCompanyId().getValue(), newCustomer.getCompany().getCompanyId().getValue());
    }
}
