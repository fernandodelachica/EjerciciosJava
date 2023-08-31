package com.formacion.graphql.category.controller;

import com.formacion.graphql.category.application.CategoryService;
import com.formacion.graphql.category.domain.Category;
import com.formacion.graphql.category.domain.dto.CategoryInputDto;
import com.formacion.graphql.category.domain.dto.CategoryOutputDto;
import com.formacion.graphql.product.domain.dto.ProductInputDto;
import com.formacion.graphql.product.domain.dto.ProductOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @QueryMapping
    public List<CategoryOutputDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @QueryMapping
    public CategoryOutputDto getCategoryById(@Argument Long id){
        return categoryService.getCategoryById(id);
    }

    @MutationMapping
    public CategoryOutputDto addCategory(@Argument CategoryInputDto categoryInputDto){
        return categoryService.addCategory(categoryInputDto);
    }

    @MutationMapping
    public CategoryOutputDto updateCategoryById(@Argument Long id, @Argument CategoryInputDto categoryInputDto){
        return categoryService.updateCategory(id, categoryInputDto);
    }

    @MutationMapping
    public String deleteCategoryById(@Argument Long id){
        return categoryService.deleteCategoryById(id);
    }
}
