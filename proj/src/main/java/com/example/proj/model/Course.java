package com.example.proj.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Date createdDate;

    private Date updatedDate;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lesson;

    @OneToMany(mappedBy = "course")
    private List<Assignment> assignment;

    @OneToMany(mappedBy = "course")
    private List<Exam> exam;

    @ManyToMany(mappedBy = "course")
    private List<Student> student;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
