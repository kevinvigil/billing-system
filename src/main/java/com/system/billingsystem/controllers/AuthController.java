package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.CustomerDto;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.services.AuthService;
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
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/auth/login/")
    public ResponseEntity<?> login(@RequestBody CustomerDto customerDto){
        if (customerDto == null)
            throw new IllegalArgumentException();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.save(CUSTOMER_MAPPER.toDomain(customerDto)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/auth/register/")
    public ResponseEntity<?> register(@RequestBody CustomerDto customerDto){
        if (customerDto == null)
            throw new IllegalArgumentException();

        try {
            this.authService.update(CUSTOMER_MAPPER.toDomain(customerDto));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            CustomerDto customerDto = CUSTOMER_MAPPER.toDto(authService.findById(new CustomerId(id)));
            if (customerDto == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.ok().body(customerDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/api/customer/")
    public ResponseEntity<?> findAll (){
        try {
            List<CustomerDto> CustomerDto = authService.findAll().stream().map(CUSTOMER_MAPPER::toDto).toList();
            return ResponseEntity.ok().body(CustomerDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
