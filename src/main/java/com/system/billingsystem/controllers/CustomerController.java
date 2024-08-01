package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.dto.dtosmappers.CustomerDtoMapper;
import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.repositories.CustomerRepository;
import com.system.billingsystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/api/user")
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
            throw new IllegalArgumentException();

        boolean isNew = customer.getCustomer_id() == null || !customerRepository.existsById(customer.getCustomer_id());

        CustomerDto customerDto = CustomerDtoMapper.toDto(customerService.save(customer));

        if (isNew)
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
        else
            return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        CustomerDto customerDto = CustomerDtoMapper.toDto(customerService.delete(id));
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            CustomerDto customerDto = CustomerDtoMapper.toDto(customerService.findById(id));
            return ResponseEntity.ok().body(customerDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll (){
        try {
            List<CustomerDto> CustomerDto = customerService.findAll().stream().map(CustomerDtoMapper::toDto).toList();
            return ResponseEntity.ok().body(CustomerDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
