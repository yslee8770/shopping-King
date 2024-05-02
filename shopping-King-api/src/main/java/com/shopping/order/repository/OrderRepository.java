package com.shopping.order.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
  List<Orders> findByMemberId(Long memberId);
}
