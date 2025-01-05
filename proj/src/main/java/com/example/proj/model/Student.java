package com.example.proj.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@DiscriminatorValue("student")
@Table(name = "students")
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date enrollmentDate;

    @OneToMany(mappedBy = "student")
    private List<Submission> submission;

    @OneToMany(mappedBy = "student")
    private List<Result> result;

    @ManyToMany
    @JoinTable(name = "enrollments",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> course;

    @OneToMany(mappedBy = "student")
    private List<Assignment> assignment;

}
