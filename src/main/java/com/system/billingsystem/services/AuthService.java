package com.system.billingsystem.services;

import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.repositories.AuthRepository;
import com.system.billingsystem.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("CustomerService")
public class AuthService {

    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    private final AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    public CustomerId save(Customer entity) {
        try {
            return authRepository.save(entity);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method save, massage: " + e.getMessage());
            throw e;
        }
    }

    public boolean update(Customer entity) {
        try {
            return this.authRepository.update(entity);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method update, massage: " + e.getMessage());
            throw e;
        }
    }

    public Customer findById(CustomerId id) {
        try {
            return authRepository.findById(id);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method findById, id: " + id + " , massage: " + e.getMessage());
            throw e;
        }
    }

    public List<Customer> findAll() {
        try {
            return this.authRepository.findAll();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method findAll, massage: " + e.getMessage());
            throw e;
        }
    }
}