package com.system.billingSystem.service;

import com.system.billingSystem.dto.UserDto;
import com.system.billingSystem.exeption.ResourceNotFoundException;
import com.system.billingSystem.model.User;
import com.system.billingSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service("UserService")
public class UserService{

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto save(User entity) {
        try {
            return new UserDto(userRepository.save(entity));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method save" + entity.toString());
            throw e;
        }
    }

    @Transactional
    public UserDto delete(Long id) {
        try {
            UserDto userDto = this.findById(id);
            userRepository.deleteById(id);
            return userDto;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error on UserService method delete, id:" + id);
            throw e;
        }
    }

    public UserDto findById(Long id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            if (user != null)
                return new UserDto(user);
            else
                throw new ResourceNotFoundException("User not found for this id :: " + id);
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error on UserService method findById, id: " + id);
            throw e;
        }
    }




}
