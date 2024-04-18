package com.shopping.category.dto;

import com.shopping.common.useStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	// 카테고리 키
	private Long categoryId;
	// 카테고리 이름
	private String name;
	// 카테고리 상태
	private useStatus status;

}
