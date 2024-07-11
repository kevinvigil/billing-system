package com.system.billingsystem.services;

import com.system.billingsystem.DTOs.CompanyDto;
import com.system.billingsystem.entities.Company;
import com.system.billingsystem.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service("CompanyService")
public class CompanyService{

    private static final Logger logger = Logger.getLogger(CompanyService.class.getName());

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyDto save(Company entity) {
        try{
            return new CompanyDto (companyRepository.save(entity));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method save, User: " + entity.toString());
            throw e;
        }
    }

    @Transactional
    public CompanyDto delete(Long id) {
        try {
            CompanyDto companyDto = this.findById(id);
            if (companyDto != null)
                this.companyRepository.deleteById(id);
            return companyDto;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method delete, id: " + id);
            throw e;
        }
    }

    public CompanyDto findById(Long id) {
        try {
            Company company = this.companyRepository.findById(id).orElse(null);
            if (company != null)
                return new CompanyDto(company);
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method findById, id: " + id );
            throw e;
        }
    }




}
