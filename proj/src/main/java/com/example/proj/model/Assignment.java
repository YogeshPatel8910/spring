package com.example.proj.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @OneToMany(mappedBy = "assignment")
    private List<Submission> submission;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;


}
