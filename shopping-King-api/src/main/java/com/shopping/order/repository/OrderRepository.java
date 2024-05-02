package com.shopping.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopping.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
