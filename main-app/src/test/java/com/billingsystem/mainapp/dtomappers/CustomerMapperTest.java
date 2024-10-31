package com.billingsystem.mainapp.dtomappers;

import com.billingsystem.mainapp.dto.CompanyDto;
import com.billingsystem.mainapp.dto.CustomerDto;
import com.billingsystem.mainapp.entities.Company;
import com.billingsystem.mainapp.entities.Customer;
import com.billingsystem.mainapp.entities.builders.companybuilder.CompanyBuilder;
import com.billingsystem.mainapp.entities.builders.customerbuilder.CustomerBuilder;
import com.billingsystem.mainapp.entities.microtypes.Address;
import com.billingsystem.mainapp.entities.microtypes.Cuit;
import com.billingsystem.mainapp.entities.microtypes.Mail;
import com.billingsystem.mainapp.entities.microtypes.Phone;
import com.billingsystem.mainapp.entities.microtypes.ids.CompanyId;
import com.billingsystem.mainapp.entities.microtypes.ids.CustomerId;
import com.billingsystem.mainapp.entities.microtypes.names.CompanyName;
import com.billingsystem.mainapp.entities.microtypes.names.CustomerName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.billingsystem.mainapp.dto.dtosmappers.CustomerMapper.CUSTOMER_MAPPER;
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
        assertEquals(customerDto.customerId(), newCustomerDto.customerId());
        assertEquals(customerDto.username(), newCustomerDto.username());
        assertEquals(customerDto.password(), newCustomerDto.password());
        assertEquals(customerDto.email(), newCustomerDto.email());
    }

    @Test
    public void shouldMapDtoToCustomer() {
        // Then
        Customer newCustomer = CUSTOMER_MAPPER.toDomain(customerDto);
        // When
        assertEquals(customer.getCustomerId(), newCustomer.getCustomerId());
        assertEquals(customer.getUsername(), newCustomer.getUsername());
        assertEquals(customer.getPassword(), newCustomer.getPassword());
        assertEquals(customer.getEmail(), newCustomer.getEmail());
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
