package com.shopping.product.dto;


import org.springframework.lang.Nullable;

import com.shopping.common.SortType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProductRequestDto {
	@Nullable
    private String category;
	@Nullable
    private String productName;
    @Nullable
    private SortType sortType;
    
    
    @Nullable
    private Integer pageNumber ;
    @Nullable
    private Integer pageSize ;
    
    
    public Integer getPageNumber() {
    	return pageNumber != null ? pageNumber : 1;
    }
    public Integer getPageSize() {
    	return pageSize != null ? pageSize : 10;
    }

}
