package com.shopping.repository;

import com.shopping.entity.Product;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findByDeletedFalse(Pageable pageable);
  Optional<Product> findByIdAndDeletedFalse(Long id);

  Page<Product> findByNameContainingAndDeletedFalse(String name, Pageable pageable);
  Page<Product> findByCategoryIdAndDeletedFalse(Long categoryId, Pageable pageable);
  Page<Product> findByNameContainingAndCategoryIdAndDeletedFalse(String name, Long categoryId, Pageable pageable);
}