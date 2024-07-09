package com.shopping.repository;

import com.shopping.entity.Category;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  Page<Category> findByDeletedFalse(Pageable pageable);
  Optional<Category> findByIdAndDeletedFalse(Long id);
}
