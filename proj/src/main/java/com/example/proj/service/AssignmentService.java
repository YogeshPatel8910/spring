package com.example.proj.service;

import com.example.proj.model.Assignment;
import com.example.proj.repositry.AssignmentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepositry assignmentRepositry;


    public Assignment getAssignmentByid(long id) {
        Optional<Assignment> byId = assignmentRepositry.findById(id);
        return byId.orElse(null);
    }
}
