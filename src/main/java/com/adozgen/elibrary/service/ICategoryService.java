package com.adozgen.elibrary.service;

import com.adozgen.elibrary.dto.request.CategoryRequest;
import com.adozgen.elibrary.dto.response.CategoryResponse;
import com.adozgen.elibrary.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    public Category getCategory(Long categoryId);
    public CategoryResponse addCategory(CategoryRequest categoryRequest);
    public CategoryResponse getCategoryById(Long categoryId);
    public List<CategoryResponse> getCategories();
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);
    public CategoryResponse deleteCategory(Long categoryId);
}
