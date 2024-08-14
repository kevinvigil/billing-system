package com.system.billingsystem.services;

import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public CompanyId save(Company entity) {
        try{
            return companyRepository.save(entity);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method save, User: " + entity.toString());
            throw e;
        }
    }

    public Company update(Company entity) {
        try {
            if (this.companyRepository.update(entity))
                return this.companyRepository.findById(entity.getCompanyId());
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method update, User: " + entity.toString());
            throw e;
        }
    }

    public Company delete(CompanyId id) {
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

    public Company findById(CompanyId id) {
        try {
            return this.companyRepository.findById(id);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method findById, id: " + id );
            throw e;
        }
    }

    public List<Company> findAll() {
        try {
            return this.companyRepository.findAll();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method findAll");
            throw e;
        }
    }
}