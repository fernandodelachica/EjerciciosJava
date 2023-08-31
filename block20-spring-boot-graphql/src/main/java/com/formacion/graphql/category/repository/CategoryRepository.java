package com.formacion.graphql.category.repository;

import com.formacion.graphql.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
