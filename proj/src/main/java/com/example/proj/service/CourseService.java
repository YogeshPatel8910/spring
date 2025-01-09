package com.example.proj.service;

import com.example.proj.dto.CategoryDTO;
import com.example.proj.dto.CourseDTO;
import com.example.proj.model.Category;
import com.example.proj.model.Course;
import com.example.proj.model.ERole;
import com.example.proj.model.Instructor;
import com.example.proj.repositry.CourseRepositry;
import com.example.proj.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Course getCourseById(Long id) {
        Optional<Course> byId = courseRepositry.findById(id);
        return byId.orElse(null);
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

    public Optional<CourseDTO> updateCourse(long id, CourseDTO courseDTO) {
        Optional<Course> course = courseRepositry.findById(id);
        if(course.isPresent()){
            Course recieved = course.get();
            recieved.setName(courseDTO.getName());
            recieved.setDescription(courseDTO.getDescription());
            recieved.setUpdatedDate(Date.valueOf(LocalDate.now()));
            return Optional.of(mapToDTO(recieved));
        }
        else
            return Optional.empty();
    }

    public boolean deleteCourse(long id) {
        boolean isPresent = courseRepositry.existsById(id);
        if(isPresent){
            courseRepositry.deleteById(id);
            return true;
        }
        else
            return false;
    }


    private List<CourseDTO> map(List<Course> courses) {
        return courses.stream().map(this::mapToDTO).toList();
    }
    private CourseDTO mapToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setCreatedDate(course.getCreatedDate());
        courseDTO.setLesson(course.getLesson());
        courseDTO.setAssignment(course.getAssignment());
        courseDTO.setExam(course.getExam());
        courseDTO.setStudent(course.getStudent());
        courseDTO.setInstructorId(course.getInstructor().getId());
        courseDTO.setCategoryId(course.getCategory().getId());
        return courseDTO;
    }

}
