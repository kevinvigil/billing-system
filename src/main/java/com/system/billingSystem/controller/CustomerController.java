package com.system.billingSystem.controller;

import com.system.billingSystem.dto.CustomerDto;
import com.system.billingSystem.exeption.BadRequestException;
import com.system.billingSystem.model.Customer;
import com.system.billingSystem.repository.CustomerRepository;
import com.system.billingSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository){
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Customer customer){
        if (customer == null)
            throw new BadRequestException("Invalid customer data");

        boolean isNew = customer.getId() == null || !customerRepository.existsById(customer.getId());

        CustomerDto customerDto = customerService.save(customer);

        if (isNew)
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
        else
            return ResponseEntity.ok().body(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        CustomerDto customerDto = customerService.delete(id);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        CustomerDto customerDto = customerService.findById(id);
        return ResponseEntity.ok().body(customerDto);
    }
}
