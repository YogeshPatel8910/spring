package com.example.proj.controller;

import com.example.proj.dto.LessonDTO;
import com.example.proj.dto.SubmissionDTO;
import com.example.proj.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> getALlSubmission(){
        try{
            List<SubmissionDTO> submissions = submissionService.getAllSubmission();
            if (submissions.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(submissions,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/student/{id}")
    public ResponseEntity<SubmissionDTO> createSubmission(@PathVariable(name = "id") long id, @RequestBody SubmissionDTO submissionDTO){
        try{
            SubmissionDTO savedSubmission = submissionService.createSubmission(id,submissionDTO);
            if(savedSubmission==null)
                return new ResponseEntity<>(savedSubmission,HttpStatus.CREATED);
            else
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/instructor/{id}")
    public ResponseEntity<Optional<SubmissionDTO>> updateSubmission(@PathVariable(name = "id") long id, @RequestBody SubmissionDTO submissionDTO){
        try {
            Optional<SubmissionDTO> updateSubmission = submissionService.updateSubmission(id,submissionDTO);
            if(updateSubmission.isPresent())
                return new ResponseEntity<>(updateSubmission,HttpStatus.OK);
            else
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteSubmission(@PathVariable(name = "id") long id){
        try{
            boolean isDeleted = submissionService.deleteSubmission(id);
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
