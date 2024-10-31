package com.billingsystem.mainapp.controllers;

import com.billingsystem.mainapp.dto.CompanyDto;
import com.billingsystem.mainapp.entities.microtypes.ids.CompanyId;
import com.billingsystem.mainapp.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.billingsystem.mainapp.dto.dtosmappers.CompanyMapper.COMPANY_MAPPER;

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
            return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(COMPANY_MAPPER.toDomain(companyDto)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PutMapping("/api/company/")
    public ResponseEntity<?> update(@RequestBody CompanyDto companyDto){
        if (companyDto == null)
            throw new IllegalArgumentException();

        try {
            this.companyService.update(COMPANY_MAPPER.toDomain(companyDto));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/api/company/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id){
        CompanyDto companyDto = COMPANY_MAPPER.toDto(companyService.delete(new CompanyId(id))) ;
        return ResponseEntity.ok().body(companyDto);
    }

    @GetMapping("/api/company/{id}")
    public ResponseEntity<?> findById (@PathVariable UUID id){
        try {
            CompanyDto companyDto = COMPANY_MAPPER.toDto(companyService.findById(new CompanyId(id)));
            if (companyDto == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.ok().body(companyDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/api/company/")
    public ResponseEntity<?> findAll (){
        try {
            List<CompanyDto> companyDto = companyService.findAll().stream().map(COMPANY_MAPPER::toDto).toList();
            return ResponseEntity.ok().body(companyDto);

        } catch (Exception e){
            throw new InternalError();
        }
    }
}
