package com.formacion.graphql.category.domain;

import com.formacion.graphql.category.domain.dto.CategoryInputDto;
import com.formacion.graphql.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(CategoryInputDto categoryInputDto){
        this.name = categoryInputDto.getName();
    }
}
