package com.example.proj.service;

import com.example.proj.dto.InstructorDTO;
import com.example.proj.dto.StudentDTO;
import com.example.proj.dto.UserDTO;
import com.example.proj.model.Instructor;
import com.example.proj.model.Student;
import com.example.proj.model.User;
import com.example.proj.repositry.InstructorRepositry;
import com.example.proj.repositry.StudentRepositry;
import com.example.proj.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class RegistrationService {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private StudentRepositry studentRepositry;

    @Autowired
    private InstructorRepositry instructorRepositry;

    public UserDTO createUser(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        User savedUsed = userRepositry.save(user);
        return mapToDTO(savedUsed);

    }
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        student.setRole(studentDTO.getRole());
        student.setEnrollmentDate(Date.valueOf(LocalDate.now()));
        Student savedUser =  studentRepositry.save(student);
        return mapToDTO(savedUser);

    }
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setName(instructorDTO.getName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPassword(instructorDTO.getPassword());
        instructor.setRole(instructorDTO.getRole());
        instructor.setSpecialization(instructorDTO.getSpecialization());
        instructor.setJoiningDate(Date.valueOf(LocalDate.now()));
        Instructor savedInstructor =  instructorRepositry.save(instructor);
        return mapToDTO(savedInstructor);
    }


    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
    private StudentDTO mapToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPassword(student.getPassword());
        studentDTO.setRole(student.getRole());
        studentDTO.setEnrollmentDate(student.getEnrollmentDate());
        return studentDTO;
    }
    private InstructorDTO mapToDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setName(instructor.getName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setPassword(instructor.getPassword());
        instructorDTO.setRole(instructor.getRole());
        instructorDTO.setSpecialization(instructor.getSpecialization());
        instructorDTO.setJoiningDate(instructor.getJoiningDate());
        return instructorDTO;
    }
}
