package com.example.proj.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class InstructorDTO extends UserDTO{

    private Long id;
    private String specialization;
    private Date joiningDate;
}
