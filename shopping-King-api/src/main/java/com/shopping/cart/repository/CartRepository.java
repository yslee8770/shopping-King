package com.shopping.cart.repository;

import java.util.List;
import java.util.Locale.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

  List<Category> findAllCategories();
}
