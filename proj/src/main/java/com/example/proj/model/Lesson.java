package com.example.proj.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

}
