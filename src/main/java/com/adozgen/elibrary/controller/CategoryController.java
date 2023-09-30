package com.adozgen.elibrary.controller;

import com.adozgen.elibrary.dto.request.AuthorRequest;
import com.adozgen.elibrary.dto.request.CategoryRequest;
import com.adozgen.elibrary.dto.response.AuthorResponse;
import com.adozgen.elibrary.dto.response.CategoryResponse;
import com.adozgen.elibrary.service.IAuthorService;
import com.adozgen.elibrary.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody final CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.addCategory(categoryRequest);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable final Long id){
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<CategoryResponse> categories = categoryService.getCategories();
        return new ResponseEntity<List<CategoryResponse>>(categories, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable final Long id) {
        CategoryResponse categoryResponse = categoryService.deleteCategory(id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody final CategoryRequest categoryRequest, @PathVariable final Long id) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }
}
