package com.system.billingSystem.services;

import com.system.billingSystem.repositories.CustomerRepository;
import com.system.billingSystem.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Customer save(Customer entity) {
        try {
            UUID uuid = customerRepository.save(entity);
            if(uuid != null)
                return customerRepository.findById(uuid);
            else
                return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method save" + entity.toString());
            throw e;
        }
    }

    public Customer update(Customer entity) {
        try {
            if (this.customerRepository.update(entity))
                return this.customerRepository.findById(entity.getCustomer_id());
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method update" + entity.toString());
            throw e;
        }
    }

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
            return customerRepository.findById(id);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method findById, id: " + id);
            throw e;
        }
    }

    public List<Customer> findAll() {
        try {
            return this.customerRepository.findAll();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method findAll");
            throw e;
        }
    }
}