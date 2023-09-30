package com.adozgen.elibrary.controller;

import com.adozgen.elibrary.dto.request.AuthorRequest;
import com.adozgen.elibrary.dto.request.BookRequest;
import com.adozgen.elibrary.dto.response.AuthorResponse;
import com.adozgen.elibrary.dto.response.BookResponse;
import com.adozgen.elibrary.service.IAuthorService;
import com.adozgen.elibrary.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BookResponse> addBook(@RequestBody final BookRequest bookRequest){
        BookResponse bookResponse = bookService.addBook(bookRequest);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable final Long id){
        BookResponse bookResponse = bookService.getBookById(id);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/Books")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        List<BookResponse> books = bookService.getBooks();
        return new ResponseEntity<List<BookResponse>>(books, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable final Long id) {
        BookResponse bookResponse = bookService.deleteBook(id);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<BookResponse> updateBook(@RequestBody final BookRequest bookRequest, @PathVariable final Long id) {
        BookResponse bookResponse = bookService.updateBook(id, bookRequest);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping("/add-category/{categoryId}/book/{bookId}")
    public ResponseEntity<BookResponse> addCategoryToBook(@PathVariable final Long categoryId, @PathVariable final Long bookId){
        BookResponse bookResponse = bookService.addCategoryToBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping("delete-category/{bookId}")
    public ResponseEntity<BookResponse> deleteCategoryFromBook(@PathVariable final Long categoryId, @PathVariable final Long bookId){
        BookResponse bookResponse = bookService.removeCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }


    @PostMapping("/add-author/{authorId}/book/{bookId}")
    public ResponseEntity<BookResponse> addAuthorToBook(@PathVariable final Long authorId, @PathVariable final Long bookId){
        BookResponse bookResponse = bookService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @PostMapping("delete-author/{bookId}")
    public ResponseEntity<BookResponse> deleteAuthorFromBook(@PathVariable final Long authorId, @PathVariable final Long bookId){
        BookResponse bookResponse = bookService.removeAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

}
