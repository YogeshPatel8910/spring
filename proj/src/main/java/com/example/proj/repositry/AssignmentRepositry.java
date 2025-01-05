package com.example.proj.repositry;

import com.example.proj.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepositry extends JpaRepository<Assignment,Long> {
}
