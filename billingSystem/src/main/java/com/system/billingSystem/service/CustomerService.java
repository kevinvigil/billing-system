package com.system.billingSystem.service;

import com.system.billingSystem.dto.CustomerDto;
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

    @Transactional
    public CustomerDto save(CustomerDto entity) {
        try {
            Customer customer = new Customer(entity);
            return new CustomerDto(this.customerRepository.save(customer));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CustomerService on method save" + entity.toString());
            throw e;
        }
    }

    @Transactional
    public CustomerDto update(CustomerDto customerDto){
        try{
            return new CustomerDto(this.customerRepository.updateById(customerDto.id(), customerDto.name(), customerDto.direction(),
                    customerDto.email(), customerDto.phone()));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CustomerService on method update");
            throw e;
        }
    }

    @Transactional
    public CustomerDto delete(Long id) {
        try{
            CustomerDto customerDto = this.findById(id);
            if (customerDto != null)
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
            return null;
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error in CustomerService on method findById on id: " + id);
            throw e;
        }
    }
}