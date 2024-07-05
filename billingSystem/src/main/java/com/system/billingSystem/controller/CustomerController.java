package com.system.billingSystem.controller;

import com.system.billingSystem.dto.CustomerDto;
import com.system.billingSystem.model.Customer;
import com.system.billingSystem.repository.CustomerRepository;
import com.system.billingSystem.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<?> save(@RequestBody CustomerDto customer){
        if (customer == null)
            throw new IllegalArgumentException();

        CustomerDto customerDto = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody CustomerDto customer){
        if (customer == null)
            throw new IllegalArgumentException();
        try {
            if (this.customerRepository.existsById(customer.id())){
                return ResponseEntity.ok().body(this.customerService.update(customer));
            }
            else
                throw new EntityNotFoundException();
        } catch (Exception e) {
            throw new InternalError();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        CustomerDto customerDto = customerService.delete(id);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            CustomerDto customerDto = customerService.findById(id);
            if (customerDto != null)
                return ResponseEntity.ok().body(customerDto);
            else
                throw new EntityNotFoundException();
        } catch (Exception e) {
            throw new InternalError();
        }
    }
}
