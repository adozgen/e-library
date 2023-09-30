package com.adozgen.elibrary.repository;

import com.adozgen.elibrary.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
