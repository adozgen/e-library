package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.request.AuthorRequest;
import com.adozgen.elibrary.dto.response.AuthorResponse;
import com.adozgen.elibrary.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAuthorService {
    public AuthorResponse addAuthor(AuthorRequest authorRequest);
    public List<AuthorResponse> getAuthors();
    public AuthorResponse getAuthorById(Long authorId);
    public Author getAuthor(Long authorId);
    public AuthorResponse deleteAuthor(Long authorId);
    public AuthorResponse updateAuthor(Long authorId, AuthorRequest authorRequest);
    public AuthorResponse addZipcodeToAuthor(Long authorId, Long zipcodeId);
    public AuthorResponse deleteZipcodeFromAuthor(Long authorId);
}
