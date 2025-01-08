package com.example.proj.controller;

import com.example.proj.dto.CourseDTO;
import com.example.proj.dto.LessonDTO;
import com.example.proj.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<LessonDTO>> getALlLesson(){
        try{
            List<LessonDTO> lessons = lessonService.getAllLesson();
            if (lessons.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lessons,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/instructor/{id}")
    public ResponseEntity<LessonDTO> createLesson(@PathVariable(name = "id") long id, @RequestBody LessonDTO lessonDTO){
        try {
            LessonDTO savedLesson = lessonService.createLesson(id,lessonDTO);
            if(savedLesson==null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(savedLesson,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/instructor/{id}")
    public ResponseEntity<Optional<LessonDTO>> updateLesson(@PathVariable(name = "id") long id,@RequestBody LessonDTO lessonDTO){
        try {
            Optional<LessonDTO> updateLesson = lessonService.updateLesson(id,lessonDTO);
            if(updateLesson.isPresent())
                return new ResponseEntity<>(updateLesson,HttpStatus.OK);
            else
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<HttpStatus> deleteLesson(@PathVariable(name = "id") long id){
        try{
            boolean isDeleted = lessonService.deleteLesson(id);
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
