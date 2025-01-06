package com.example.proj.service;

import com.example.proj.dto.InstructorDTO;
import com.example.proj.model.Course;
import com.example.proj.model.Instructor;
import com.example.proj.model.User;
import com.example.proj.repositry.InstructorRepositry;
import com.example.proj.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepositry instructorRepositry;

    public List<InstructorDTO> getAllInstructors(){
        return map(instructorRepositry.findAll());
    }

    private List<InstructorDTO> map(List<Instructor> instructors) {
        return instructors.stream().map(this::mapToDTO).toList();
    }

    private InstructorDTO mapToDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setName(instructor.getName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setSpecialization(instructor.getSpecialization());
        instructorDTO.setJoiningDate(instructor.getJoiningDate());
        instructorDTO.setCoursesId(instructor.getCourse().stream().map(Course::getId).toList());
        return instructorDTO;
    }

    public Instructor getInstructorById(long id){
        Optional<Instructor> byId = instructorRepositry.findById(id);
        return byId.orElse(null);
    }
}
