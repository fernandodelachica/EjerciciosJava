package com.formacion.graphql.product.domain;

import com.formacion.graphql.category.domain.Category;
import com.formacion.graphql.product.domain.dto.ProductInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;

    @ManyToOne
    private Category category;

    public Product(ProductInputDto productInputDto){
        this.id = UUID.randomUUID().toString();
        this.name = productInputDto.getName();
        this.price = productInputDto.getPrice();
        this.quantity = productInputDto.getQuantity();
    }

}
