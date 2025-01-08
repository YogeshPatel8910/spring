package com.example.proj.service;

import com.example.proj.dto.ExamDTO;
import com.example.proj.dto.LessonDTO;
import com.example.proj.model.Course;
import com.example.proj.model.Exam;
import com.example.proj.model.Lesson;
import com.example.proj.repositry.ExamRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepositry examRepositry;

    @Autowired
    private CourseService courseService;

    public  List<ExamDTO> getAllExam() {
        return map(examRepositry.findAll());
    }


    private  List<ExamDTO> map(List<Exam> exams) {
        return exams.stream().map(this::mapToDTO).toList();
    }

    private ExamDTO mapToDTO(Exam exam) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setTitle(exam.getTitle());
        examDTO.setDate(exam.getDate());
        examDTO.setDuration(exam.getDuration());
        examDTO.setCourseId(exam.getCourse().getId());
        examDTO.setResult(exam.getResult());
        return examDTO;
    }

    public ExamDTO createExam(long id, ExamDTO examDTO) {
        Course course = courseService.getCourseById(id);
        Exam exam = new Exam();
        exam.setTitle(examDTO.getTitle());
        exam.setDate(examDTO.getDate());
        exam.setDuration(examDTO.getDuration());
        exam.setCourse(course);
        Exam savedExam = examRepositry.save(exam);
        return mapToDTO(savedExam);
    }

    public Optional<ExamDTO> updateExam(long id, ExamDTO examDTO) {
        Optional<Exam> exam = examRepositry.findById(id);
        if(exam.isPresent()){
            Exam recieved = exam.get();
            recieved.setDate(examDTO.getDate());
            return Optional.of(mapToDTO(recieved));
        }
        else
            return Optional.empty();
    }

    public boolean deleteExam(long id) {
        boolean isPresent = examRepositry.existsById(id);
        if(isPresent){
            examRepositry.deleteById(id);
            return true;
        }
        else
            return false;
    }
}
