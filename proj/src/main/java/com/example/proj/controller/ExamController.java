package com.example.proj.controller;

import com.example.proj.dto.ExamDTO;
import com.example.proj.dto.LessonDTO;
import com.example.proj.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getALlExam(){
        try{
            List<ExamDTO> exams = examService.getAllExam();
            if (exams.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(exams,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/instructor/{id}")
    public ResponseEntity<ExamDTO> createExam(@PathVariable(name = "id") long id, @RequestBody ExamDTO examDTO){
        try {
            ExamDTO savedExam = examService.createExam(id,examDTO);
            if(savedExam==null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(savedExam,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/instructor/{id}")
    public ResponseEntity<Optional<ExamDTO>> updateExam(@PathVariable(name = "id") long id, @RequestBody ExamDTO examDTO){
        try {
            Optional<ExamDTO> updateExam = examService.updateExam(id,examDTO);
            if(updateExam.isPresent())
                return new ResponseEntity<>(updateExam,HttpStatus.OK);
            else
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteExam(@PathVariable(name = "id") long id){
        try{
            boolean isDeleted = examService.deleteExam(id);
            if(isDeleted){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
