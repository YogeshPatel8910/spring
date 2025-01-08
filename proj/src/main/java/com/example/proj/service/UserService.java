package com.example.proj.service;

import com.example.proj.dto.InstructorDTO;
import com.example.proj.dto.StudentDTO;
import com.example.proj.dto.UserDTO;
import com.example.proj.model.User;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public interface UserService {
    UserDTO createUser(UserDTO userDTO);
}
