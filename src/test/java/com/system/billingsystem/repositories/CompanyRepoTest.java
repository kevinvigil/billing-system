package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Company;
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

    private static UUID baseId;

    @Autowired
    public CompanyRepoTest(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @BeforeEach
    public void setUp() {
        company = Company.builder()
                .name("company")
                .cuit("1111")
                .email("company@hotmail.com")
                .phone("1111")
                .direction("hello world")
                .build();

        baseId = companyRepository.save(company);
        assertNotNull(baseId);
        company.setCompany_id(baseId);
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
        company.setName("updatedCompany");
        assertTrue(companyRepository.update(company));
        Company newCompany = companyRepository.findById(baseId);
        assertNotNull(newCompany);
        assertEquals("updatedCompany", newCompany.getName());
    }

    @Test
    public void testFindAll(){
        Company newCompany = Company.builder()
                .company_id(new UUID(2,2))
                .name("company2")
                .cuit("2222")
                .email("company2@hotmail.com")
                .phone("1111")
                .direction("hello world2")
                .build();

        UUID newCompanyId = companyRepository.save(newCompany);
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