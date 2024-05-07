package com.shopping.cart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> findByMemberId(Long memberId);
}
