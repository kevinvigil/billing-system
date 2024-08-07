package com.system.billingsystem.services;

import com.system.billingSystem.entities.Company;
import com.system.billingSystem.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @MockBean
    private CompanyRepository companyRepository;

    private Company company;

    @BeforeEach
    void setUp(){
         company = Company.builder()
                 .company_id(UUID.randomUUID())
                .name("company")
                .cuit("1111")
                .email("company@hotmail.com")
                .phone("1111")
                .direction("hello world")
                .build();
    }

    @Test
    public void testFindById(){
        Mockito.when(companyRepository.findById(company.getCompany_id())).thenReturn(company);
        assertNotNull(companyService.findById(company.getCompany_id()));
    }
}
