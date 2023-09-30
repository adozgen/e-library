package com.adozgen.elibrary.repository;

import com.adozgen.elibrary.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
