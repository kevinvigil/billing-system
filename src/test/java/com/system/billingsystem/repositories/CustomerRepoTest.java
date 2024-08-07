package com.system.billingsystem.repositories;

import com.system.billingSystem.entities.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepoTest {

    private final CustomerRepository customerRepository;

    private static Customer customer;

    private static UUID baseId;

    @Autowired
    public CustomerRepoTest(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    public void setUp() {
        customer = Customer.builder()
                .name("user name")
                .email("user@hotmail.com")
                .password("userPassword")
                .company(null)
                .build();
        
        baseId = customerRepository.save(customer);
        assertNotNull(baseId);
        customer.setCustomer_id(baseId);
    }

    @AfterEach
    public void tearDown() {
        customerRepository.deleteById(baseId);
    }

    @Test
    public void testFindById() {
        Customer foundCustomer = customerRepository.findById(baseId);
        assertNotNull(foundCustomer);
        assertEquals(baseId, foundCustomer.getCustomer_id());
    }

    @Test
    public void testUpdateUser() {
        customer.setName("new name");
        assertTrue(customerRepository.update(customer));
        Customer foundCustomer = customerRepository.findById(baseId);
        assertNotNull(foundCustomer);
        assertEquals(customer.getName(), foundCustomer.getName());
    }

    @Test
    public void testFindAll(){
        Customer newCustomer = Customer.builder()
                .name("user2 name")
                .email("user2@hotmail.com")
                .password("user2Password")
                .company(null)
                .build();

        UUID newId = customerRepository.save(newCustomer);
        assertNotNull(newId);

        List<Customer> foundCustomers = customerRepository.findAll();
        System.out.println(foundCustomers);
        assertNotNull(foundCustomers);
        assertTrue(foundCustomers.size() > 1);
        customerRepository.deleteById(newId);
    }

    @Test
    public void testExistById(){
        assertTrue(customerRepository.existsById(baseId));
    }
}
