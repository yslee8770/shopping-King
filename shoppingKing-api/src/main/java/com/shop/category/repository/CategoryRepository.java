package com.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
