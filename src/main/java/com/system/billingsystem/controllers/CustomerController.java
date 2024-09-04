package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.services.CustomerService;
import com.system.billingsystem.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.CustomerMapper.CUSTOMER_MAPPER;

@Controller
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/api/customer/")
    public ResponseEntity<?> save(@RequestBody CustomerDto customerDto){
        if (customerDto == null)
            throw new IllegalArgumentException();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(CUSTOMER_MAPPER.toDomain(customerDto)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/api/customer/")
    public ResponseEntity<?> update(@RequestBody CustomerDto customerDto){
        if (customerDto == null)
            throw new IllegalArgumentException();

        Customer customer = this.customerService.update(CUSTOMER_MAPPER.toDomain(customerDto));

        return ResponseEntity.ok(CUSTOMER_MAPPER.toDto(customer));
    }

    @DeleteMapping("/api/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        CustomerDto customerDto = CUSTOMER_MAPPER.toDto(customerService.delete(new CustomerId(id)));
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            CustomerDto customerDto = CUSTOMER_MAPPER.toDto(customerService.findById(new CustomerId(id)));
            return ResponseEntity.ok().body(customerDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/api/customer/")
    public ResponseEntity<?> findAll (){
        try {
            List<CustomerDto> CustomerDto = customerService.findAll().stream().map(CUSTOMER_MAPPER::toDto).toList();
            return ResponseEntity.ok().body(CustomerDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
