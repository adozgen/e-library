package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.mapper.CategoryMapper;
import com.adozgen.elibrary.dto.request.CategoryRequest;
import com.adozgen.elibrary.dto.response.CategoryResponse;
import com.adozgen.elibrary.model.Category;
import com.adozgen.elibrary.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->
                new IllegalArgumentException("Kategori bulunamadı: "+categoryId));
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
       Category category = getCategory(categoryId);
       return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<Category> categories = StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return categoryMapper.categoriesToCategoryResponses(categories);
    }

    @Transactional
    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = getCategory(categoryId);
        category.setName(categoryRequest.getName());
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return categoryMapper.categoryToCategoryResponse(category);
    }


}
