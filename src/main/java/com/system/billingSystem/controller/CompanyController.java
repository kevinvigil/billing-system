package com.system.billingSystem.controller;

import com.system.billingSystem.dto.CompanyDto;
import com.system.billingSystem.exeption.BadRequestException;
import com.system.billingSystem.model.Company;
import com.system.billingSystem.repository.CompanyRepository;
import com.system.billingSystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Company company){
        if (company == null)
            throw new BadRequestException("Invalid company data");

        boolean isNew = company.getId()  == null || !companyRepository.existsById(company.getId());

        companyService.save(company);

        if (isNew)
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        else
            return ResponseEntity.ok().body(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        CompanyDto companyDto = companyService.delete(id);
        return ResponseEntity.ok().body(companyDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Long id){
        CompanyDto companyDto = companyService.findById(id);
        return ResponseEntity.ok().body(companyDto);
    }
}
