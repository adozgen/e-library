package com.adozgen.elibrary.dto.mapper;

import com.adozgen.elibrary.dto.response.BookResponse;
import com.adozgen.elibrary.model.Author;
import com.adozgen.elibrary.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookResponse bookToBookResponse(Book book) {
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        bookResponse.setAuthorNames(book.getAuthors().stream()
                .map(Author::getName)
                .collect(Collectors.toList()));
        bookResponse.setId(book.getId());
        bookResponse.setName(book.getName());
        bookResponse.setCategoryName(book.getCategory().getName());
        return bookResponse;
    }

    public List<BookResponse> booksToBookResponses(List<Book> books) {
        return books.stream()
                .map(this::bookToBookResponse)
                .collect(Collectors.toList());
    }
}