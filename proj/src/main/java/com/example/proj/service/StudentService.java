package com.example.proj.service;

import com.example.proj.model.Student;
import com.example.proj.repositry.StudentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepositry studentRepositry;

    public List<Student> getAllStudent(){
        return studentRepositry.findAll();
    }
}
