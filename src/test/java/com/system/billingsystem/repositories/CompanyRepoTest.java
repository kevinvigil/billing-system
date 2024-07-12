package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class CompanyRepoTest {

    @Autowired
    private CompanyRepository companyRepository;

    private static Company company;

    @BeforeAll
    public static void setUp() {
        company = Company.builder()
                .id(new UUID(1,1))
                .name("company")
                .cuit("1111")
                .email("company@hotmail.com")
                .phone("1111")
                .direction("hello world")
                .build();
    }

    @AfterEach
    void tearDown(){
        companyRepository.deleteAll();
    }

    @Test
    public void testCreateCompany() {

        Company newCompany = companyRepository.save(company);
        
        assertNotNull(newCompany);

        assertTrue(newCompany.getId().compareTo(new UUID(1,0)) > 0);
    }

    @Test
    public void testFindById() {
        Company newCompany = companyRepository.save(company);
        assertNotNull(newCompany);
        Company newCompany2 = companyRepository.findById(newCompany.getId()).orElse(null);
        assertNotNull(newCompany2);
        assertEquals("company", newCompany2.getName());
    }

    @Test
    public void testUpdateCompany() {
        Company newCompany = companyRepository.save(company);
        assertNotNull(newCompany);

        newCompany.setName("updatedCompany");
        companyRepository.save(newCompany);
        Company newCompany2 = companyRepository.findById(newCompany.getId()).get();
        assertNotNull(newCompany2);

        assertEquals("updatedCompany", newCompany2.getName());
    }

    @Test
    public void testDeleteCompany() {
        Company newCompany = companyRepository.save(company);
        assertNotNull(newCompany);
        companyRepository.deleteById(newCompany.getId());
        assertNull(Optional.of(companyRepository.findById(newCompany.getId())).get().orElse(null) );
    }

    @Test
    public void testFindAll(){
        Company newCompany = Company.builder()
                .id(new UUID(2,2))
                .name("company2")
                .cuit("2222")
                .email("company2@hotmail.com")
                .phone("1111")
                .direction("hello world2")
                .build();
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        companies.add(newCompany);

        companyRepository.saveAll(companies);

        List<Company> companies2 = companyRepository.findAll();
        assertNotNull(companies2);
        assertEquals(2, companies2.size());
    }

    @Test
    public void testSaveAll(){
        Company newCompany = new Company(new UUID(2,2));
        newCompany.setCuit("2222");
        newCompany.setEmail("company2@hotmail.com");
        List<Company> companies = new ArrayList<>();
        companies.add(company);
        companies.add(newCompany);

        companyRepository.saveAll(companies);

        List<Company> newInvoices = companyRepository.findAll();
        assertNotNull(newInvoices);
        assertEquals(companies.size(), newInvoices.size());
    }
}
