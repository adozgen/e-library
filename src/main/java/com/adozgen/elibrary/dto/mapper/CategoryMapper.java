package com.adozgen.elibrary.dto.mapper;


import com.adozgen.elibrary.dto.response.CategoryResponse;
import com.adozgen.elibrary.model.Book;
import com.adozgen.elibrary.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryResponse categoryToCategoryResponse(Category category) {
        CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
        categoryResponse.setId(category.getId());
        categoryResponse.setName(categoryResponse.getName());
        categoryResponse.setBookNames(category.getBooks().stream().map(Book::getName).collect(Collectors.toList()));
        return categoryResponse;
    }

    public List<CategoryResponse> categoriesToCategoryResponses(List<Category> categories) {
        return categories.stream()
                .map(this::categoryToCategoryResponse)
                .collect(Collectors.toList());
    }
}