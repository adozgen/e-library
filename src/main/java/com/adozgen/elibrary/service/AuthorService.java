package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.mapper.AuthorMapper;
import com.adozgen.elibrary.dto.request.AuthorRequest;
import com.adozgen.elibrary.dto.response.AuthorResponse;
import com.adozgen.elibrary.model.Author;
import com.adozgen.elibrary.model.Zipcode;
import com.adozgen.elibrary.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService implements IAuthorService {

    private final AuthorRepository authorRepository;
    private final IZipcodeService zipcodeService;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, IZipcodeService zipcodeService, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorResponse addAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        if (authorRequest.getZipcodeId() == null) {
            throw new IllegalArgumentException("ZipcodeId cannot be null");
        }
        Zipcode zipcode = zipcodeService.getZipcode(authorRequest.getZipcodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        return authorMapper.authorToAuthorResponse(author);
    }

    @Override
    public List<AuthorResponse> getAuthors() {
        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return authorMapper.authorsToAuthorResponses(authors);
    }

    @Override
    public AuthorResponse getAuthorById(Long authorId) {
        return authorMapper.authorToAuthorResponse(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(()->
                new IllegalArgumentException("Author not found"));
    }

    @Override
    public AuthorResponse deleteAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        authorRepository.delete(author);
        return authorMapper.authorToAuthorResponse(author);
    }

    @Override
    public AuthorResponse updateAuthor(Long authorId, AuthorRequest authorRequest) {
        Author author = getAuthor(authorId);
        author.setName(authorRequest.getName());
        if (authorRequest.getZipcodeId() != null) {
            Zipcode zipcode = zipcodeService.getZipcode(authorRequest.getZipcodeId());
            author.setZipcode(zipcode);
        }
        return authorMapper.authorToAuthorResponse(author);
    }

    @Transactional
    @Override
    public AuthorResponse addZipcodeToAuthor(Long authorId, Long zipcodeId) {
        Author author = getAuthor(authorId);
        Zipcode zipcode = zipcodeService.getZipcode(zipcodeId);
        if (Objects.nonNull(author.getZipcode())){
            throw new IllegalArgumentException("Author already has a zipcode");
        }
        author.setZipcode(zipcode);
        return authorMapper.authorToAuthorResponse(author);
    }

    @Transactional
    @Override
    public AuthorResponse deleteZipcodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipcode(null);
        return authorMapper.authorToAuthorResponse(author);
    }
}
