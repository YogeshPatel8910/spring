package com.example.proj.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignmentId")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    private String submissionFile;

    private String grade;


}
