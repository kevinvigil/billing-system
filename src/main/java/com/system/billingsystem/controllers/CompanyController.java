package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.dto.dtosmappers.CompanyDtoMapper;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.services.CompanyService;
import com.system.billingsystem.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @PostMapping("/api/company/")
    public ResponseEntity<?> save(@RequestBody CompanyDto companyDto){
        if (companyDto == null)
            throw new IllegalArgumentException();
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(CompanyDtoMapper.toDomain(companyDto)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/api/company/")
    public ResponseEntity<?> update(@RequestBody CompanyDto companyDto){
        if (companyDto == null)
            throw new IllegalArgumentException();

        Company company = this.companyService.update(CompanyDtoMapper.toDomain(companyDto));

        return ResponseEntity.status(HttpStatus.OK).body(CompanyDtoMapper.toDto(company));
    }

    @DeleteMapping("/api/company/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id){
        CompanyDto companyDto = CompanyDtoMapper.toDto(companyService.delete(new CompanyId(id))) ;
        return ResponseEntity.ok().body(companyDto);
    }

    @GetMapping("/api/company/{id}")
    public ResponseEntity<?> findById (@PathVariable UUID id){
        try {
            CompanyDto companyDto = CompanyDtoMapper.toDto(companyService.findById(new CompanyId(id)));
            return ResponseEntity.ok().body(companyDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/api/company/")
    public ResponseEntity<?> findAll (){
        try {
            List<CompanyDto> companyDto = companyService.findAll().stream().map(CompanyDtoMapper::toDto).toList();
            return ResponseEntity.ok().body(companyDto);

        } catch (Exception e){
            throw new InternalError();
        }
    }
}
