package com.shopping.order.repository;

import com.shopping.order.entity.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

  List<Orders> findByMemberId(Long memberId);
}
