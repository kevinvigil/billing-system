package com.billingsystem.authservice.controller;

import com.billingsystem.authservice.entity.dto.CustomerDto;
import com.billingsystem.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller("AuthController")
@RestController
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/login/")
    public String login(@RequestBody CustomerDto customerDto){
        return  "jaja";
        //if (customerDto == null)
        //    throw new IllegalArgumentException();
//
        //try {
        //    boolean authenticated = authService.login(customerDto.getUsername(), customerDto.getPassword());
        //    if (authenticated) {
        //        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authenticated);
        //    }
        //    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticated);
        //}catch (Exception e){
        //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        //}
    }

    @PutMapping("/auth/register/")
    public ResponseEntity<?> register(@RequestBody CustomerDto customerDto){
        if (customerDto == null)
            throw new IllegalArgumentException();

        try {
            //boolean authenticated = this.authService.register(customerDto.getUsername(), customerDto.getEmail(), customerDto.getPassword());

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
