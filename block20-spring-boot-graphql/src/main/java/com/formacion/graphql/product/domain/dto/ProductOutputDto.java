package com.formacion.graphql.product.domain.dto;

import com.formacion.graphql.category.domain.Category;
import com.formacion.graphql.category.domain.dto.CategoryOutputDto;
import com.formacion.graphql.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOutputDto {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private Category category;

    public ProductOutputDto(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.category = product.getCategory();
    }
}
