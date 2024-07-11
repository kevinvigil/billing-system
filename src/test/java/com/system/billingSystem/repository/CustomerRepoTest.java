package com.system.billingSystem.repository;

import com.system.billingSystem.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepoTest {

    @Autowired
    private CustomerRepository userRepo;

    private static Customer customer;

    @BeforeAll
    public static void setUp() {
        customer = Customer.builder()
                .id(1L)
                .name("user name")
                .email("user@hotmail.com")
                .password("userPassword")
                .company(null)
                .build();
    }

    @AfterEach
    public void tearDown() {
        userRepo.deleteAll();
    }

    @Test
    public void testSaveUser() {
        Customer newUser = userRepo.save(customer);
        assertNotNull(newUser);
        assertEquals(customer.getEmail(), newUser.getEmail());
    }

    @Test
    public void testFindById() {
        Customer newUser = userRepo.save(customer);
        assertNotNull(newUser);
        Customer foundUser = userRepo.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getId(), foundUser.getId());
    }

    @Test
    public void testDeleteUser() {
        Customer newUser = userRepo.save(customer);
        assertNotNull(newUser);
        Customer foundUser = userRepo.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getId(), foundUser.getId());
        userRepo.deleteById(newUser.getId());
        assertNull(userRepo.findById(newUser.getId()).orElse(null));
    }

    @Test
    public void testUpdateUser() {
        Customer newUser = userRepo.save(customer);
        assertNotNull(newUser);
        newUser.setName("new name");
        userRepo.save(newUser);
        Customer foundUser = userRepo.findById(newUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(newUser.getName(), foundUser.getName());
    }
}
