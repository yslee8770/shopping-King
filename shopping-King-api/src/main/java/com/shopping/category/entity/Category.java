package com.shopping.category.entity;

import com.shopping.common.useStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Category {
  // 카테고리 키
  @Id
  private Long categoryId;
  // 카테고리 이름
  private String name;
  // 카테고리 상태
  private useStatus status;
}
