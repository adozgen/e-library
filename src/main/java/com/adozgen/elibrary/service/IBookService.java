package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.request.BookRequest;
import com.adozgen.elibrary.dto.response.BookResponse;
import com.adozgen.elibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
    public BookResponse addBook(BookRequest bookRequest);
    public BookResponse getBookById(Long bookId);
    public Book getBook(Long bookId);
    public List<BookResponse> getBooks();
    public BookResponse deleteBook(Long bookId);
    public BookResponse updateBook(Long bookId, BookRequest bookRequest);
    public BookResponse addAuthorToBook(Long bookId, Long authorId);
    public BookResponse removeAuthorFromBook(Long bookId, Long authorId);
    public BookResponse addCategoryToBook(Long bookId, Long categoryId);
    public BookResponse removeCategoryFromBook(Long bookId, Long categoryId);
}
