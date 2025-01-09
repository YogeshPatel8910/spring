package com.example.proj.controller;

import com.example.proj.dto.CategoryDTO;
import com.example.proj.model.Category;
import com.example.proj.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        try{
            List<Category> categories = categoryService.getCategory();
            if (categories.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable(name = "id") long id,@RequestBody CategoryDTO categoryDTO){
        try{

            Optional<CategoryDTO> newCategory = categoryService.updateCategory(id,categoryDTO);
            if(newCategory.isPresent()){
                return new ResponseEntity<>(newCategory.get(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") long id){
        try{
            if(categoryService.deleteCategory(id))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
