package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.microtypes.Address;
import com.system.billingsystem.entities.microtypes.Cuit;
import com.system.billingsystem.entities.microtypes.Phone;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.names.CompanyName;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepoTest {

    private final CompanyRepository companyRepository;

    private static Company company;

    private static CompanyId baseId;

    @Autowired
    public CompanyRepoTest(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @BeforeEach
    public void setUp() {
        company = Company.builder()
                .name(new CompanyName("company"))
                .cuit(new Cuit("1111"))
                .email("company@hotmail.com")
                .phone(new Phone("+54", "1111", "1111"))
                .address(new Address("hello world","hello world","hello world")) //TODO
                .build();

        baseId = companyRepository.save(company);
        assertNotNull(baseId);
        company.setCompanyId(baseId);
    }

    @AfterEach
    void tearDown(){
        companyRepository.deleteById(baseId);
        Company proof = companyRepository.findById(baseId);
        assertNull(proof);
    }

    @Test
    public void testFindById() {
        Company newCompany = companyRepository.findById(baseId);
        assertNotNull(newCompany);
        assertEquals("company", newCompany.getName());
    }

    @Test
    public void testUpdateCompany() {
        company.setName(new CompanyName("updatedCompany"));
        assertTrue(companyRepository.update(company));
        Company newCompany = companyRepository.findById(baseId);
        assertNotNull(newCompany);
        assertEquals("updatedCompany", newCompany.getName());
    }

    @Test
    public void testFindAll(){
        Company newCompany = Company.builder()
                .companyId(new CompanyId(UUID.randomUUID()))
                .name(new CompanyName("company"))
                .cuit(new Cuit("1111"))
                .email("company@hotmail.com")
                .phone(new Phone("+54", "1111", "1111"))
                .address(new Address("hello world","hello world","hello world")) //TODO
                .build();

        CompanyId newCompanyId = companyRepository.save(newCompany);
        assertNotNull(newCompanyId);

        List<Company> companies = companyRepository.findAll();
        assertNotNull(companies);
        assertTrue(companies.size() > 1);
        companyRepository.deleteById(newCompanyId);
    }

    @Test
    public void testExistsById(){
        assertTrue(companyRepository.existsById(baseId));
    }
}