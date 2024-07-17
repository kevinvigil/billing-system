package com.system.billingsystem.services;

import com.system.billingsystem.entities.Company;
import com.system.billingsystem.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
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
    public Company save(Company entity) {
        try{
            return companyRepository.save(entity);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method save, User: " + entity.toString());
            throw e;
        }
    }

    @Transactional
    public Company delete(UUID id) {
        try {
            Company company = this.findById(id);
            if (company != null)
                this.companyRepository.deleteById(id);
            return company;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method delete, id: " + id);
            throw e;
        }
    }

    public Company findById(UUID id) {
        try {
            return this.companyRepository.findById(id).orElse(null);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method findById, id: " + id );
            throw e;
        }
    }
}
