package com.formacion.graphql.product.application;

import com.formacion.graphql.category.domain.Category;
import com.formacion.graphql.category.repository.CategoryRepository;
import com.formacion.graphql.product.domain.Product;
import com.formacion.graphql.product.domain.dto.ProductInputDto;
import com.formacion.graphql.product.domain.dto.ProductOutputDto;
import com.formacion.graphql.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductOutputDto> productList(){
        List<Product> products = productRepository.findAll();
        List<ProductOutputDto> productsOutput = new ArrayList<>();
        for(Product product : products){
            productsOutput.add(new ProductOutputDto(product));
        }
        return productsOutput;
    }

    @Override
    public ProductOutputDto getProductById(String id){
        Product product = findProductById(id);

        return new ProductOutputDto(product);
    }

    @Override
    public ProductOutputDto addProduct(ProductInputDto productInputDto){
        Product product = new Product(productInputDto);

        Category category = findCategoryById(productInputDto);
        product.setCategory(category);

        productRepository.save(product);

        return new ProductOutputDto(product);
    }

    @Override
    public ProductOutputDto updateProduct(String id, ProductInputDto productInputDto){
        Product product = findProductById(id);

        product.setName(productInputDto.getName());
        product.setPrice(productInputDto.getPrice());
        product.setQuantity(productInputDto.getQuantity());
        product.setCategory(findCategoryById(productInputDto));

        productRepository.save(product);

        return new ProductOutputDto(product);
    }

    @Override
    public String deleteProductId(String id){
        productRepository.deleteById(id);

        return "El producto con el id "+id+" ha sido eliminado";
    }


    // Extracted multi-used Methods
    private Product findProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(()->
                new RuntimeException("El producto con el id "+ id +" no existe."));
        return product;
    }

    private Category findCategoryById(ProductInputDto productInputDto) {
        Category category = categoryRepository.findById(productInputDto.getCategory()).orElseThrow(()->
                new RuntimeException("La categoría "+ productInputDto.getCategory()+" no existe."));
        return category;
    }
}
