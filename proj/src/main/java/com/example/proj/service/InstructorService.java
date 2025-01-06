package com.example.proj.service;

import com.example.proj.model.Instructor;
import com.example.proj.model.User;
import com.example.proj.repositry.InstructorRepositry;
import com.example.proj.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepositry instructorRepositry;

    public List<Instructor> getAllInstructors(){
        return instructorRepositry.findAll();
    }
}
