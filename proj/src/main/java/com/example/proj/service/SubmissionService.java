package com.example.proj.service;

import com.example.proj.dto.LessonDTO;
import com.example.proj.dto.SubmissionDTO;
import com.example.proj.model.Assignment;
import com.example.proj.model.Lesson;
import com.example.proj.model.Student;
import com.example.proj.model.Submission;
import com.example.proj.repositry.StudentRepositry;
import com.example.proj.repositry.SubmissionRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepositry submissionRepositry;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AssignmentService assignmentService;

    public List<SubmissionDTO> getAllSubmission() {
        return map(submissionRepositry.findAll());
    }

    public SubmissionDTO createSubmission(long id, SubmissionDTO submissionDTO) {
        Student student = studentService.getStudentById(id);
        Assignment assignment = assignmentService.getAssignmentByid(id);
        Submission submission = new Submission();
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setSubmissionFile(submissionDTO.getSubmissionFile());
        Submission savedSubmission = submissionRepositry.save(submission);
        return mapToDTO(savedSubmission);
    }

    public Optional<SubmissionDTO> updateSubmission(long id, SubmissionDTO submissionDTO) {
        Optional<Submission> submission = submissionRepositry.findById(id);
        if(submission.isPresent()){
            Submission recieved = submission.get();
            recieved.setGrade(submissionDTO.getGrade());
            return Optional.of(mapToDTO(recieved));
        }
        else
            return Optional.empty();
    }

    public boolean deleteSubmission(long id) {
        boolean isPresent = submissionRepositry.existsById(id);
        if(isPresent){
            submissionRepositry.deleteById(id);
            return true;
        }
        else
            return false;
    }


    private List<SubmissionDTO> map(List<Submission> submissions) {
        return submissions.stream().map(this::mapToDTO).toList();
    }
    private SubmissionDTO mapToDTO(Submission submission){
        SubmissionDTO submissionDTO = new SubmissionDTO();
        submissionDTO.setId(submission.getId());
        submissionDTO.setAssignmentId(submission.getAssignment().getId());
        submissionDTO.setStudentId(submission.getStudent().getId());
        submissionDTO.setSubmissionFile(submission.getSubmissionFile());
        submissionDTO.setGrade(submission.getGrade());
        return submissionDTO;
    }

}
