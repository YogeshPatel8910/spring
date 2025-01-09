package com.example.proj.service;

import com.example.proj.dto.InstructorDTO;
import com.example.proj.dto.UserDTO;
import com.example.proj.model.Course;
import com.example.proj.model.Instructor;
import com.example.proj.model.User;
import com.example.proj.repositry.InstructorRepositry;
import com.example.proj.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("instructorService")
public class InstructorService implements UserService{

    @Autowired
    private InstructorRepositry instructorRepositry;

    public List<InstructorDTO> getAllInstructors(){
        return map(instructorRepositry.findAll());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Instructor instructor = new Instructor();
        InstructorDTO instructorDTO = (InstructorDTO) userDTO;
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPassword(instructorDTO.getPassword());
        instructor.setRole(instructorDTO.getRole());
        instructor.setSpecialization(instructorDTO.getSpecialization());
        instructor.setJoiningDate(Date.valueOf(LocalDate.now()));
        Instructor savedInstructor =  instructorRepositry.save(instructor);
        return mapToDTO(savedInstructor);
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





//
//    private InstructorDTO mapToDTO(Instructor instructor) {
//        InstructorDTO instructorDTO = new InstructorDTO();
//        instructorDTO.setId(instructor.getId());
//        instructorDTO.setName(instructor.getName());
//        instructorDTO.setEmail(instructor.getEmail());
//        instructorDTO.setPassword(instructor.getPassword());
//        instructorDTO.setRole(instructor.getRole());
//        instructorDTO.setSpecialization(instructor.getSpecialization());
//        instructorDTO.setJoiningDate(instructor.getJoiningDate());
//        return instructorDTO;
//    }

}
