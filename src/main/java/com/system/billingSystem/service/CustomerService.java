package com.system.billingSystem.service;

import com.system.billingSystem.dto.CustomerDto;
import com.system.billingSystem.exeption.ResourceNotFoundException;
import com.system.billingSystem.model.Customer;
import com.system.billingSystem.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CustomerDto save(Customer entity) {
        try {
            return new CustomerDto(this.customerRepository.save(entity));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CustomerService on method save" + entity.toString());
            throw e;
        }
    }

    @Transactional
    public CustomerDto delete(Long id) {
        try{
            CustomerDto customerDto = this.findById(id);
            this.customerRepository.deleteById(id);
            return customerDto;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CustomerService on method delete id: " + id);
            throw e;
        }
    }

    public CustomerDto findById(Long id) {
        try {
            Customer customer = this.customerRepository.findById(id).orElse(null);

            if (customer != null)
                return new CustomerDto(customer);
            else
                throw new ResourceNotFoundException("Customer not found for this id :: " + id);

        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CustomerService on method findById on id: " + id);
            throw e;
        }
    }
}