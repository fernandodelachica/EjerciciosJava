package com.formacion.graphql.category.application;

import com.formacion.graphql.category.domain.Category;
import com.formacion.graphql.category.domain.dto.CategoryInputDto;
import com.formacion.graphql.category.domain.dto.CategoryOutputDto;
import com.formacion.graphql.category.repository.CategoryRepository;
import com.formacion.graphql.product.domain.dto.ProductOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryOutputDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryOutputDto> categoriesOutput = new ArrayList<>();
        for(Category category : categories){
            categoriesOutput.add(new CategoryOutputDto(category));
        }
        return categoriesOutput;
    }

    @Override
    public CategoryOutputDto getCategoryById(Long id){
        Category category = findCategoryById(id);

        return new CategoryOutputDto(category);
    }

    @Override
    public CategoryOutputDto addCategory(CategoryInputDto categoryInputDto){
        Category category = new Category(categoryInputDto);

        categoryRepository.save(category);

        return new CategoryOutputDto(category);
    }

    @Override
    public CategoryOutputDto updateCategory(Long id, CategoryInputDto categoryInputDto){
        Category category = findCategoryById(id);
        category.setName(categoryInputDto.getName());

        categoryRepository.save(category);
        return new CategoryOutputDto(category);
    }

    @Override
    public String deleteCategoryById(Long id){
        Category category = findCategoryById(id);
        if(!category.getProducts().isEmpty()){
            return "La categoría no puede ser eliminada, contiene productos.";
        } else{
            categoryRepository.deleteById(id);
            return "La Categoría con el id "+id+" ha sido eliminada";
        }


    }


    // Extracted multi-used Methods
    private Category findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->
                new RuntimeException("La Categoría con el id "+ id +" no existe."));
        return category;
    }

}
