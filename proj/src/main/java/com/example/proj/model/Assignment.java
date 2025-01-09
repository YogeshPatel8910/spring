package com.example.proj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "assignment")
    private List<Submission> submission;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;


}
