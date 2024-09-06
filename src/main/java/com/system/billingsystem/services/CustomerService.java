package com.system.billingsystem.services;

import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.repositories.CustomerRepository;
import com.system.billingsystem.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public CustomerId save(Customer entity) {
        try {
            return customerRepository.save(entity);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method save, massage: " + e.getMessage());
            throw e;
        }
    }

    public Customer update(Customer entity) {
        try {
            if (this.customerRepository.update(entity))
                return this.customerRepository.findById(entity.getCustomerId());
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method update, massage: " + e.getMessage());
            throw e;
        }
    }

    public Customer delete(CustomerId id) {
        try {
            Customer Customer = this.findById(id);
            if(Customer != null)
                customerRepository.deleteById(id);
            return Customer;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error on UserService method delete, id:" + id +", massage: " + e.getMessage());
            throw e;
        }
    }

    public Customer findById(CustomerId id) {
        try {
            return customerRepository.findById(id);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method findById, id: " + id + " , massage: " + e.getMessage());
            throw e;
        }
    }

    public List<Customer> findAll() {
        try {
            return this.customerRepository.findAll();
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CompanyService on method findAll, massage: " + e.getMessage());
            throw e;
        }
    }
}