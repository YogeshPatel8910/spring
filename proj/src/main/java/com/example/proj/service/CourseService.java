package com.example.proj.service;

import com.example.proj.dto.CategoryDTO;
import com.example.proj.dto.CourseDTO;
import com.example.proj.model.Category;
import com.example.proj.model.Course;
import com.example.proj.model.Instructor;
import com.example.proj.repositry.CourseRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepositry courseRepositry;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CategoryService categoryService;


    public List<CourseDTO> getAllCourse(String filter) {
        if(filter==null)
            return map(courseRepositry.findAll());
        else{
            return map(courseRepositry.findAll(Sort.by(filter)));
        }
    }

    private List<CourseDTO> map(List<Course> courses) {
        return courses.stream().map(this::mapToDTO).toList();
    }

    public CourseDTO createCourse(long id, CourseDTO courseDTO) {
        Course course = new Course();
        Instructor instructor = instructorService.getInstructorById(id);
        Category category = categoryService.getCategoryById(courseDTO.getCategoryId());
        if(instructor==null||category==null){
            return null;
        }
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setCreatedDate(Date.valueOf(LocalDate.now()));
        course.setInstructor(instructor);
        course.setCategory(category);
        Course savedCourse = courseRepositry.save(course);
        return mapToDTO(savedCourse);
    }

    private CourseDTO mapToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setCreatedDate(course.getCreatedDate());
        courseDTO.setInstructorId(course.getInstructor().getId());
        courseDTO.setCategoryId(course.getCategory().getId());
        return courseDTO;
    }
}
