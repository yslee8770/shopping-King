package com.shopping.repository;

import com.shopping.entity.ProductImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ProductImage, Long> {
  List<ProductImage> findByProductId(Long productId);
}
