package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.entities.microtypes.Mail;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.entities.microtypes.names.CustomerName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepoTest {

    private final AuthRepository authRepository;

    private static Customer customer;

    private static CustomerId baseId;

    @Autowired
    public CustomerRepoTest(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @BeforeEach
    public void setUp() {
        customer = Customer.builder()
                .name(new CustomerName("user name","user name","user name"))
                .email(new Mail("user2@hotmail.com"))
                .password("userPassword")
                .company(null)
                .build();
        
        baseId = authRepository.save(customer);
        assertNotNull(baseId);
        customer.setCustomerId(baseId);
    }

    @AfterEach
    public void tearDown() {
        authRepository.deleteById(baseId);
    }

    @Test
    public void testFindById() {
        Customer foundCustomer = authRepository.findById(baseId);
        assertNotNull(foundCustomer);
        assertEquals(baseId, foundCustomer.getCustomerId());
    }

    @Test
    public void testUpdateUser() {
        customer.setName(new CustomerName("new name","new name","new name"));
        assertTrue(authRepository.update(customer));
        Customer foundCustomer = authRepository.findById(baseId);
        assertNotNull(foundCustomer);
        assertEquals(customer.getName().getFirstName(), foundCustomer.getName().getFirstName());
    }

    @Test
    public void testFindAll(){
        Customer newCustomer = Customer.builder()
                .name(new CustomerName("user2 name","user2 name","user2 name"))
                .email(new Mail("user2@hotmail.com"))
                .password("user3Password")
                .company(null)
                .build();

        CustomerId newId = authRepository.save(newCustomer);
        assertNotNull(newId);

        List<Customer> foundCustomers = authRepository.findAll();
        System.out.println(foundCustomers);
        assertNotNull(foundCustomers);
        assertTrue(foundCustomers.size() > 1);
        authRepository.deleteById(newId);
    }

    @Test
    public void testExistById(){
        assertTrue(authRepository.existsById(baseId));
    }
}
