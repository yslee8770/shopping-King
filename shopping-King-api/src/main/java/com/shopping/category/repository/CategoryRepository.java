package com.shopping.category.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findAllCategories();
}
