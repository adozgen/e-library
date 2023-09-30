package com.adozgen.elibrary.dto.mapper;

import com.adozgen.elibrary.dto.response.AuthorResponse;
import com.adozgen.elibrary.dto.response.BookResponse;
import com.adozgen.elibrary.model.Author;
import com.adozgen.elibrary.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    private final ModelMapper modelMapper;

    public AuthorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AuthorResponse authorToAuthorResponse(Author author) {
        AuthorResponse authorResponse = modelMapper.map(author, AuthorResponse.class);
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setBookNames(author.getBooks().stream()
                .map(Book::getName)
                .collect(Collectors.toList()));
        authorResponse.setZipcodeName(author.getZipcode().getName());
        return authorResponse;
    }

    public List<AuthorResponse> authorsToAuthorResponses(List<Author> authors) {
        return authors.stream()
                .map(this::authorToAuthorResponse)
                .collect(Collectors.toList());
    }
}