package com.shopping.product.repository;

import com.shopping.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByCategoryId(Long categoryId);
}
