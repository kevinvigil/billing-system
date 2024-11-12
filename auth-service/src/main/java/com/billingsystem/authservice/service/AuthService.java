package com.billingsystem.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("AuthService")
public class AuthService {

    public boolean login(String username, String password) {
        return true;
    }

    public boolean register(String username, String mail, String password) {
        return true;
    }
}
