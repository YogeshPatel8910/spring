package com.example.proj.service;

import com.example.proj.model.User;
import com.example.proj.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepositry userRepositry;

    public List<User> getUsers(){
        return userRepositry.findAll();
    }
}
