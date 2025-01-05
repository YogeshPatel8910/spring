package com.example.proj.repositry;

import com.example.proj.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepositry extends JpaRepository<Exam, Long>{

        }
