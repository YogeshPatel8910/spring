package com.example.proj.service;

import com.example.proj.dto.StudentDTO;
import com.example.proj.dto.UserDTO;
import com.example.proj.model.Student;
import com.example.proj.repositry.StudentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("studentService")
public class StudentService implements UserService{

    @Autowired
    private StudentRepositry studentRepositry;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Student student = new Student();
        StudentDTO studentDTO = (StudentDTO) userDTO;
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        student.setRole(studentDTO.getRole());
        student.setEnrollmentDate(Date.valueOf(LocalDate.now()));
        Student savedUser =  studentRepositry.save(student);
        return mapToDTO(savedUser);

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

    public List<Student> getAllStudent(){
        return studentRepositry.findAll();
    }

    public Student getStudentById(long id) {
        Optional<Student> byId = studentRepositry.findById(id);
        return byId.orElse(null);
    }
}
