package com.shopping.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long id;

  private String name;
  private String description;
  private double price;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
  private Inventory inventory;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  @Builder.Default
  private List<ProductImage> images = new ArrayList<>();

  private boolean deleted;

  public void update(String name, String description, double price, Category category) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.category = category;
  }

  public void delete() {
    this.deleted = true;
  }
}
