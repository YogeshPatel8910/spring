package com.example.proj.repositry;

import com.example.proj.model.Course;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositry extends JpaRepository<Course,Long> {
}
