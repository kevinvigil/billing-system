package com.system.billingsystem.services;

import com.system.billingsystem.DTOs.CustomerDto;
import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("UserService")
public class UserService{

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final CustomerRepository customerRepository;

    @Autowired
    public UserService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDto save(Customer entity) {
        try {
            return new CustomerDto (customerRepository.save(entity));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method save" + entity.toString());
            throw e;
        }
    }

    @Transactional
    public CustomerDto delete(UUID id) {
        try {
            CustomerDto customerDto = this.findById(id);
            if(customerDto != null)
                customerRepository.deleteById(id);
            return customerDto;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error on UserService method delete, id:" + id);
            throw e;
        }
    }

    public CustomerDto findById(UUID id) {
        try {
            Customer user = customerRepository.findById(id).orElse(null);
            if (user != null)
                return new CustomerDto (user);
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method findById, id: " + id);
            throw e;
        }
    }
}