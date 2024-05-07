package com.shop.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByCategoryId(Long categoryId);
}
