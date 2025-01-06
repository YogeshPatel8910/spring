package com.example.proj.controller;

import com.example.proj.dto.InstructorDTO;
import com.example.proj.dto.StudentDTO;
import com.example.proj.dto.UserDTO;
import com.example.proj.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        try{
            if(userDTO instanceof StudentDTO){
                StudentDTO studentDTO = (StudentDTO) userDTO;
                StudentDTO newStudent = registrationService.createStudent(studentDTO);
                return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
            }
            else if (userDTO instanceof InstructorDTO) {
                InstructorDTO instructorDTO = (InstructorDTO) userDTO;
                InstructorDTO newInstructor = registrationService.createInstructor(instructorDTO);
                return new ResponseEntity<>(newInstructor, HttpStatus.CREATED);}

            else{
                UserDTO newUser = registrationService.createUser(userDTO);
                return new ResponseEntity<>(newUser,HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
