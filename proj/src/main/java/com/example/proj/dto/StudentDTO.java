package com.example.proj.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentDTO extends UserDTO {
        private Long id;
        private Date enrollmentDate;

}
