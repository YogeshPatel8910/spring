package com.example.proj.repositry;

import com.example.proj.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepositry extends JpaRepository<Submission,Long> {
}
