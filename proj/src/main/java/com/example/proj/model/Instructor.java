package com.example.proj.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("instructor")
@Table(name = "instructors")
public class Instructor extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialization;

    private Date joiningDate;

    @OneToMany(mappedBy = "instructor")
    private List<Course> course;


}
