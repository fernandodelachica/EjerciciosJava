package com.formacion.graphql.product.controller;

import com.formacion.graphql.product.application.ProductService;
import com.formacion.graphql.product.domain.Product;
import com.formacion.graphql.product.domain.dto.ProductInputDto;
import com.formacion.graphql.product.domain.dto.ProductOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<ProductOutputDto> productList(){
        return productService.productList();
    }

    @QueryMapping
    public ProductOutputDto getProductById(@Argument String id){return productService.getProductById(id);
    }

    @MutationMapping
    public ProductOutputDto addProduct(@Argument ProductInputDto productInputDto){
        return productService.addProduct(productInputDto);
    }

    @MutationMapping
    public ProductOutputDto updateProductById(@Argument String id, @Argument ProductInputDto productInputDto){
        return productService.updateProduct(id, productInputDto);
    }

    @MutationMapping
    public String deleteProductById(@Argument String id){
        return productService.deleteProductId(id);
    }
}
