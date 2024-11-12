//package com.billingsystem.authservice.dtomapper;
//
//import com.billingsystem.authservice.entity.Customer;
//import com.billingsystem.authservice.entity.builder.customerbuilder.CustomerBuilder;
//import com.billingsystem.authservice.entity.dto.CustomerDto;
//import com.billingsystem.authservice.entity.microtypes.Id;
//import com.billingsystem.authservice.entity.microtypes.Mail;
//import com.billingsystem.authservice.entity.microtypes.Name;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class CustomerMapperTest {
//
//    private Customer customer;
//    private CustomerDto customerDto;
//
//
//    @BeforeAll
//    public static void setUpBeforeClass(){}
//
//    @BeforeEach
//    public void setUp() {
//        // Given
//        UUID baseId = UUID.randomUUID();
//
//        customer = CustomerBuilder.newBuilder()
//                .CustomerId(new Id(baseId))
//                .name(new Name("first","second","surname"))
//                .password("password")
//                .email(new Mail("customer@customer.com"))
//                .build();
//
//        customerDto = new CustomerDto(
//                baseId,
//                "first second surname",
//                "customer@customer.com",
//                "password"
//        );
//    }
//
//    @Test
//    public void shouldMapCustomerToDto() {
//        //Then
//        CustomerDto newCustomerDto = CUSTOMER_MAPPER.toDto(customer);
//
//        //When
//        assertEquals(customerDto.customerId(), newCustomerDto.customerId());
//        assertEquals(customerDto.username(), newCustomerDto.username());
//        assertEquals(customerDto.password(), newCustomerDto.password());
//        assertEquals(customerDto.email(), newCustomerDto.email());
//    }
//
//    @Test
//    public void shouldMapDtoToCustomer() {
//        // Then
//        Customer newCustomer = CUSTOMER_MAPPER.toDomain(customerDto);
//        // When
//        assertEquals(customer.getCustomerId(), newCustomer.getCustomerId());
//        assertEquals(customer.getUsername(), newCustomer.getUsername());
//        assertEquals(customer.getPassword(), newCustomer.getPassword());
//        assertEquals(customer.getEmail(), newCustomer.getEmail());
//    }
//
//    @Test
//    public void shouldMapCustomerToDtoWhitCompany() {
//        // Given
//        // Then
//        CustomerDto newCustomerDto = CUSTOMER_MAPPER.toDto(customer);
//
//        // When
//        assertNotNull(newCustomerDto);
//        assertNotNull(newCustomerDto.customerId());
//        assertEquals(customer.getCustomerId().getValue(), newCustomerDto.customerId());
//    }
//
//    @Test
//    public void shouldMapDtoToCustomerWhitCompany() {
//        // Given
//        customerDto = new CustomerDto(
//                customer.getCustomerId().getValue(),
//                "first second surname",
//                "customer@customer.com",
//                "password"
//        );
//
//        // Then
//        Customer newCustomer = CUSTOMER_MAPPER.toDomain(customerDto);
//
//        // When
//        assertNotNull(newCustomer);
//        assertNotNull(newCustomer.getCustomerId());
//        assertEquals(customer.getCustomerId().getValue(), newCustomer.getCustomerId().getValue());
//    }
//}
