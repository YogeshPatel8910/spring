package com.example.proj.controller;

import com.example.proj.dto.UserDTO;
import com.example.proj.service.UserFactory;
import com.example.proj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private UserFactory userFactory;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        try{
            UserService userService = userFactory.getService(userDTO.getRole());
            UserDTO createdUser = userService.createUser(userDTO);
            return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
