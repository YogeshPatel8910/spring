package com.example.proj.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "examId")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private  Student student;

    private Integer marksObtained;

}
