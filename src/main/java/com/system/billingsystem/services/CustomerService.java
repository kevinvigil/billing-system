package com.system.billingsystem.services;

import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("CustomerService")
public class CustomerService {

    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer save(Customer entity) {
        try {
            return customerRepository.save(entity);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method save" + entity.toString());
            throw e;
        }
    }

    @Transactional
    public Customer delete(UUID id) {
        try {
            Customer Customer = this.findById(id);
            if(Customer != null)
                customerRepository.deleteById(id);
            return Customer;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error on UserService method delete, id:" + id);
            throw e;
        }
    }

    public Customer findById(UUID id) {
        try {
            return customerRepository.findById(id).orElse(null);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method findById, id: " + id);
            throw e;
        }
    }
}