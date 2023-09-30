package com.adozgen.elibrary.controller;

import com.adozgen.elibrary.dto.request.AuthorRequest;
import com.adozgen.elibrary.dto.response.AuthorResponse;
import com.adozgen.elibrary.model.Zipcode;
import com.adozgen.elibrary.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final IAuthorService authorService;

    @Autowired
    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody final AuthorRequest authorRequest){
        AuthorResponse authorResponse = authorService.addAuthor(authorRequest);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable final Long id){
        AuthorResponse authorResponse = authorService.getAuthorById(id);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors(){
        List<AuthorResponse> authors = authorService.getAuthors();
        return new ResponseEntity<List<AuthorResponse>>(authors, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AuthorResponse> deleteAuthor(@PathVariable final Long id) {
        AuthorResponse authorResponse = authorService.deleteAuthor(id);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@RequestBody final AuthorRequest authorRequest, @PathVariable final Long id) {
        AuthorResponse authorResponse = authorService.updateAuthor(id, authorRequest);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @PostMapping("/add-zipcode/{zipcodeId}/author/{authorId}")
    public ResponseEntity<AuthorResponse> addZipcodeToAuthor(@PathVariable final Long zipcodeId, @PathVariable final Long authorId){
        AuthorResponse authorResponse = authorService.addZipcodeToAuthor(authorId, zipcodeId);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }

    @PostMapping("delete-zipcode/{zipcodeId}")
    public ResponseEntity<AuthorResponse> deleteZipcodeFromAuthor(@PathVariable final Long zipcodeId){
        AuthorResponse authorResponse = authorService.deleteZipcodeFromAuthor(zipcodeId);
        return new ResponseEntity<>(authorResponse, HttpStatus.OK);
    }
}
