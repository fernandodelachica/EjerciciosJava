package com.formacion.graphql.product.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInputDto {
    private String name;
    private double price;
    private int quantity;
    private Long category;
}
