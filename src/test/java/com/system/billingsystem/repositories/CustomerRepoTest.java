package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepoTest {

    @Autowired
    private CustomerRepository customerRepository;

    private static Customer customer;

    @BeforeAll
    public static void setUp() {
        customer = Customer.builder()
                .id(new UUID(1,1))
                .name("user name")
                .email("user@hotmail.com")
                .password("userPassword")
                .company(null)
                .build();
    }

    @AfterEach
    public void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        Customer newUser = customerRepository.save(customer);
        assertNotNull(newUser);
        assertEquals(customer.getEmail(), newUser.getEmail());
    }

    @Test
    public void testFindById() {
        Customer newUser = customerRepository.save(customer);
        assertNotNull(newUser);
        Customer foundUser = customerRepository.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getId(), foundUser.getId());
    }

    @Test
    public void testDeleteUser() {
        Customer newUser = customerRepository.save(customer);
        assertNotNull(newUser);
        Customer foundUser = customerRepository.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getId(), foundUser.getId());
        customerRepository.deleteById(newUser.getId());
        assertNull(customerRepository.findById(newUser.getId()).orElse(null));
    }

    @Test
    public void testUpdateUser() {
        Customer newUser = customerRepository.save(customer);
        assertNotNull(newUser);
        newUser.setName("new name");
        customerRepository.save(newUser);
        Customer foundUser = customerRepository.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getName(), foundUser.getName());
    }
}
