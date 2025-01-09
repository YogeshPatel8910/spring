package com.example.proj.repositry;

import com.example.proj.dto.LessonDTO;
import com.example.proj.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepositry extends JpaRepository<Lesson,Long> {
}
