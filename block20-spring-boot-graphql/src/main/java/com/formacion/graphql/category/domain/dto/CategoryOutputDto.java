package com.formacion.graphql.category.domain.dto;

import com.formacion.graphql.category.domain.Category;
import com.formacion.graphql.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryOutputDto {
    private Long id;
    private String name;
    private List<Product> products;

    public CategoryOutputDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.products = category.getProducts();
    }
}
