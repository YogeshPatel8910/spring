package com.example.proj.dto;

import com.example.proj.model.Instructor;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;

import java.sql.Date;

@Data
public class CourseDTO {
    private Long id;

    private String name;

    private String description;

    private Date createdDate;

    private Date updatedDate;

    private long instructorId;

    private long categoryId;

}
