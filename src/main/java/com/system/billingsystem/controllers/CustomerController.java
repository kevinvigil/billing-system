package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.dto.dtosmappers.CustomerDtoMapper;
import com.system.billingsystem.entities.Customer;
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
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody CustomerDto customerDto){
        if (customerDto == null)
            throw new IllegalArgumentException();

        Customer customer = this.customerService.save(CustomerDtoMapper.toDomain(customerDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerDtoMapper.toDto(customer));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody CustomerDto customerDto){
        if (customerDto == null)
            throw new IllegalArgumentException();

        Customer customer = this.customerService.update(CustomerDtoMapper.toDomain(customerDto));

        return ResponseEntity.ok(CustomerDtoMapper.toDto(customer));
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
