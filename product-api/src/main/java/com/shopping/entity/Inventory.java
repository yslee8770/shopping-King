package com.shopping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventory_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private int quantity;

  public void updateQuantity(int quantity) {
    this.quantity = quantity;
  }
}
