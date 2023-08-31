package com.formacion.graphql.product.application;

import com.formacion.graphql.product.domain.Product;
import com.formacion.graphql.product.domain.dto.ProductInputDto;
import com.formacion.graphql.product.domain.dto.ProductOutputDto;

import java.util.List;

public interface ProductService {

    List<ProductOutputDto> productList();

    ProductOutputDto getProductById(String id);

    ProductOutputDto addProduct(ProductInputDto productInputDto);

    ProductOutputDto updateProduct(String id, ProductInputDto productInputDto);

    String deleteProductId(String id);
}
