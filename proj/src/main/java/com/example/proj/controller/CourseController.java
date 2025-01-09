package com.example.proj.controller;

import com.example.proj.dto.CategoryDTO;
import com.example.proj.dto.CourseDTO;
import com.example.proj.model.Category;
import com.example.proj.model.Course;
import com.example.proj.model.Instructor;
import com.example.proj.service.CourseService;
import com.example.proj.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourse(@RequestParam(required = false) String filter){
        try{
            List<CourseDTO> courses = courseService.getAllCourse(filter);
            if (courses.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/instructor/{id}")
    public ResponseEntity<CourseDTO> createCourse(@PathVariable(name = "id")long id,@RequestBody CourseDTO courseDTO){
        try {
            CourseDTO savedCourse = courseService.createCourse(id,courseDTO);
            if(savedCourse==null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(savedCourse,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("instructor/{id}")
    public ResponseEntity<Optional<CourseDTO>> updateCourse(@PathVariable(name = "id")long id, @RequestBody CourseDTO courseDTO){
        try{
            Optional<CourseDTO> updatedCourse = courseService.updateCourse(id,courseDTO);
            if(updatedCourse.isPresent())
                return new ResponseEntity<>(updatedCourse,HttpStatus.OK);
            else
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable(name = "id")long id,@RequestBody CourseDTO courseDTO){
        try{
            boolean isDeleted = courseService.deleteCourse(id);
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
