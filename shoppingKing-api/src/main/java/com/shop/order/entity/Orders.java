package com.shop.order.entity;

import java.time.LocalDateTime;
import com.shop.common.OrderStatus;
import com.shop.member.entity.Member;
import com.shop.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

  @Id
  @GeneratedValue
  @Column(name = "order_id")
  private Long id;

  @Column(name = "order_price")
  private int price;

  @Column(name = "order_dt")
  private LocalDateTime orderDt;

  @Column(name = "order_quantity")
  private int quantity;

  @Column(name = "order_address")
  private String address;

  @Enumerated(EnumType.STRING)
  @JoinColumn(name = "order_status")
  private OrderStatus orderStatus;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

}
