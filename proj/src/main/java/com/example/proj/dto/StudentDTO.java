package com.example.proj.dto;

import com.example.proj.model.Assignment;
import com.example.proj.model.Course;
import com.example.proj.model.Result;
import com.example.proj.model.Submission;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class StudentDTO extends UserDTO {
        private Long id;

        private Date enrollmentDate;

        private List<Course> course;

        private List<Assignment> assignment;

        private List<Submission> submission;

        private List<Result> result;
}
