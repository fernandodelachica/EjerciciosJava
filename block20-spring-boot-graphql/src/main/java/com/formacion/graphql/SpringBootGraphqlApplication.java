package com.formacion.graphql;

import com.formacion.graphql.category.domain.Category;
import com.formacion.graphql.category.repository.CategoryRepository;
import com.formacion.graphql.product.domain.Product;
import com.formacion.graphql.product.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringBootGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphqlApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, ProductRepository productRepository){

		Random random = new Random();

		return args -> {
			List.of("Ordenadores", "Impresoras", "Smartphones", "Papelería").forEach(cat -> {
				Category category = new Category();
				category.setName(cat);
				categoryRepository.save(category);
			});
			categoryRepository.findAll().forEach(category -> {
				for (int i = 0; i < 10; i++){
					Product product = new Product();
					product.setId(UUID.randomUUID().toString());
					product.setName("Ordenadores "+i);
					product.setPrice(100+Math.random()*50000);
					product.setQuantity(random.nextInt(100));
					product.setCategory(category);
					productRepository.save(product);
				}
			});
		};
	}

}
