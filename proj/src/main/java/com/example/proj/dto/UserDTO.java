package com.example.proj.dto;

import com.example.proj.model.ERole;
import lombok.Builder;
import lombok.Data;

@Data
    public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private ERole role;

}
