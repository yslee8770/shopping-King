package com.shopping.cart.repository;

import com.shopping.cart.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

  List<Cart> findByMemberId(Long memberId);
}
