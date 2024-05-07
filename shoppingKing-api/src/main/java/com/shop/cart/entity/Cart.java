package com.shop.cart.entity;

import com.shop.common.DeleteAt;
import com.shop.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
@Builder
public class Cart {
  @Id
  @GeneratedValue
  @Column(name = "cart_id")
  private Long id;

  @Column(name = "member_id")
  private Long memberId;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "cart_price")
  private int price;

  private int quantity;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "delete_at")
  private DeleteAt deleteAt;

}
