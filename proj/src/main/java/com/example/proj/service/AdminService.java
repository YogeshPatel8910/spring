package com.example.proj.service;

import com.example.proj.model.User;
import com.example.proj.repositry.InstructorRepositry;
import com.example.proj.repositry.StudentRepositry;
import com.example.proj.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private StudentRepositry studentRepositry;

    @Autowired
    private InstructorRepositry instructorRepositry;


    public List<User> getUsers(){
        return userRepositry.findAll();
    }

    public boolean deleteUser(long id){
        Optional<User> user = userRepositry.findById(id);
        if(user.isPresent()) {
            int role = user.get().getRole().ordinal();
            switch (role) {
                case 0:
                    userRepositry.deleteById(id);
                    break;
                case 1:
                    instructorRepositry.deleteById(id);
                    break;
                case 2:
                    studentRepositry.deleteById(id);
                    break;
                default:
                    break;
            }
            return true;
        }
        else
            return false;
    }
}
