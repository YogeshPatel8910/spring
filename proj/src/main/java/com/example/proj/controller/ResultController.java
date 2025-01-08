package com.example.proj.controller;

import com.example.proj.dto.ResultDTO;
import com.example.proj.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;
    
    @GetMapping("/student/{id}")
    public ResponseEntity<ResultDTO> getALlResult(@PathVariable(name = "id") long id){
        try{
            Optional<ResultDTO> results = resultService.getResultById(id);
            if (results.isPresent()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(results.get(),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/instructor/{id}")
//    public ResponseEntity<ResultDTO> createResult(@PathVariable(name = "id") long id, @RequestBody ResultDTO resultDTO){
//        try {
//            ResultDTO savedResult = resultService.createResult(id,resultDTO);
//            if(savedResult==null)
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//            else
//                return new ResponseEntity<>(savedResult,HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PutMapping("/instructor/{id}")
    public ResponseEntity<Optional<ResultDTO>> updateResult(@PathVariable(name = "id") long id, @RequestBody ResultDTO resultDTO){
        try {
            Optional<ResultDTO> updateResult = resultService.updateResult(id,resultDTO);
            if(updateResult.isPresent())
                return new ResponseEntity<>(updateResult,HttpStatus.OK);
            else
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<HttpStatus> deleteResult(@PathVariable(name = "id") long id){
        try{
            boolean isDeleted = resultService.deleteResult(id);
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
