package com.system.billingSystem.controller;

import com.system.billingSystem.dto.UserDto;
import com.system.billingSystem.model.User;
import com.system.billingSystem.repository.UserRepository;
import com.system.billingSystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody User user){
        if (user == null)
            throw new IllegalArgumentException();

        boolean isNew = user.getId() == null || !userRepository.existsById(user.getId());

        UserDto userDto = userService.save(user);

        if (isNew)
            return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
        else
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        UserDto userDto = userService.delete(id);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            UserDto userDto = userService.findById(id);
            if (userDto == null)
                return ResponseEntity.ok().body(userDto);
            else
                throw new EntityNotFoundException();
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
