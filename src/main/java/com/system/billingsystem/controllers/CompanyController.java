package com.system.billingsystem.controllers;

import com.system.billingsystem.DTOs.CompanyDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.repositories.CompanyRepository;
import com.system.billingsystem.services.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
            throw new IllegalArgumentException();

        boolean isNew = company.getId()  == null || !companyRepository.existsById(company.getId());

        companyService.save(company);

        if (isNew)
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        else
            return ResponseEntity.ok().body(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id){
        CompanyDto companyDto = companyService.delete(id);
        return ResponseEntity.ok().body(companyDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable UUID id){
        try {
            CompanyDto companyDto = companyService.findById(id);
            if (companyDto != null)
                return ResponseEntity.ok().body(companyDto);
            else
                throw new EntityNotFoundException();

        } catch (Exception e){
            throw new InternalError();
        }
    }
}
