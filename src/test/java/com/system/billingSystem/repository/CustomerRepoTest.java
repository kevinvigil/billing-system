package com.system.billingSystem.repository;

import com.system.billingSystem.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepoTest {

    @Autowired
    private CustomerRepository customerRepo;

    private static Customer customer;

    @BeforeAll
    public static void setUp (){
        customer = Customer.builder()
                .id(1L)
                .name("Customer")
                .direction("Customer 9000")
                .phone("2150 555555")
                .cuit("9999")
                .email("customer@hotmail.com")
                .build();
    }

    @AfterEach
    public void tearDown(){
        customerRepo.deleteAll();
    }

    @Test
    public void testSaveCustomer(){
        Customer newCustomer = customerRepo.save(customer);

        assertNotNull(newCustomer);

        assertEquals(customer.getEmail(), newCustomer.getEmail());
    }

    @Test
    public void testFindCustomerById(){
        Customer newCustomer = customerRepo.save(customer);
        assertNotNull(newCustomer);
        Customer foundCustomer = customerRepo.findById(newCustomer.getId()).orElse(null);
        assertNotNull(foundCustomer);
        assertEquals(newCustomer.getId(), foundCustomer.getId());
    }
    
    @Test
    public void testUpdateCustomer(){
        Customer newCustomer = customerRepo.save(customer);
        String newName = "newName";
        newCustomer.setName(newName);
        customerRepo.save(newCustomer);
        Customer updatedCustomer = customerRepo.findById(newCustomer.getId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals(newName, updatedCustomer.getName());
    }

    @Test
    public void testDeleteCustomer(){
        Customer newCustomer = customerRepo.save(customer);
        Customer foundCustomer = customerRepo.findById(newCustomer.getId()).orElse(null);
        assertNotNull(foundCustomer);
        customerRepo.deleteById(newCustomer.getId());
        foundCustomer = customerRepo.findById(newCustomer.getId()).orElse(null);
        assertNull(foundCustomer);
    }

    @Test
    public void findAllCustomers(){
        customerRepo.save(customer);
        customerRepo.save(Customer.builder()
                .id(2L)
                .name("Customer2")
                .direction("Customer2 9000")
                .phone("22150 555555")
                .cuit("99992")
                .email("customer2@hotmail.com")
                .build());

        List<Customer> customers = customerRepo.findAll();
        assertNotNull(customers);
        assertEquals(2, customers.size());
    }

    @Test
    public void saveAllCustomers(){
        Customer newCustomer = Customer.builder()
                .id(2L)
                .name("Customer2")
                .direction("Customer2 9000")
                .phone("22150 555555")
                .cuit("99992")
                .email("customer2@hotmail.com")
                .build();

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(newCustomer);

        customerRepo.saveAll(customers);
        List<Customer> foundCustomers = customerRepo.findAll();

        assertNotNull(foundCustomers);
        assertEquals(2, foundCustomers.size());
    }
}
