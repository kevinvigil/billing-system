package com.system.billingsystem.controllers;

import com.system.billingsystem.DTOs.CustomerDto;
import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.repositories.CustomerRepository;
import com.system.billingsystem.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/user")
public class CustomerController {

    private final UserService userService;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(UserService userService, CustomerRepository customerRepository){
        this.userService = userService;
        this.customerRepository = customerRepository;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Customer user){
        if (user == null)
            throw new IllegalArgumentException();

        boolean isNew = user.getId() == null || !customerRepository.existsById(user.getId());

        CustomerDto customerDto = userService.save(user);

        if (isNew)
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
        else
            return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        CustomerDto customerDto = userService.delete(id);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            CustomerDto customerDto = userService.findById(id);
            if (customerDto == null)
                return ResponseEntity.ok().body(customerDto);
            else
                throw new EntityNotFoundException();
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
