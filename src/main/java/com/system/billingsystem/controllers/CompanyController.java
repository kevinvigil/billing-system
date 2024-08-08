package com.system.billingSystem.controllers;

import com.system.billingSystem.dto.CompanyDto;
import com.system.billingSystem.dto.dtosmappers.CompanyDtoMapper;
import com.system.billingSystem.services.CompanyService;
import com.system.billingSystem.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CompanyDto companyDto){
        if (companyDto == null)
            throw new IllegalArgumentException();

        Company company = companyService.save(CompanyDtoMapper.toDomain(companyDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(CompanyDtoMapper.toDto(company));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CompanyDto companyDto){
        if (companyDto == null)
            throw new IllegalArgumentException();

        Company company = this.companyService.update(CompanyDtoMapper.toDomain(companyDto));

        return ResponseEntity.status(HttpStatus.OK).body(CompanyDtoMapper.toDto(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id){
        CompanyDto companyDto = CompanyDtoMapper.toDto(companyService.delete(id)) ;
        return ResponseEntity.ok().body(companyDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable UUID id){
        try {
            CompanyDto companyDto = CompanyDtoMapper.toDto(companyService.findById(id));
            return ResponseEntity.ok().body(companyDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll (){
        try {
            List<CompanyDto> companyDto = companyService.findAll().stream().map(CompanyDtoMapper::toDto).toList();
            return ResponseEntity.ok().body(companyDto);

        } catch (Exception e){
            throw new InternalError();
        }
    }
}
