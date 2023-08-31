package com.formacion.graphql.category.application;

import com.formacion.graphql.category.domain.dto.CategoryInputDto;
import com.formacion.graphql.category.domain.dto.CategoryOutputDto;

import java.util.List;

public interface CategoryService {
    List<CategoryOutputDto> getAllCategories();

    CategoryOutputDto getCategoryById(Long id);

    CategoryOutputDto addCategory(CategoryInputDto categoryInputDto);

    CategoryOutputDto updateCategory(Long id, CategoryInputDto categoryInputDto);

    String deleteCategoryById(Long id);
}
