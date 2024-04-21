package com.shopping.category.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shopping.category.dto.CategoryDto;
import com.shopping.common.useStatus;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	//카테고리 전체조회
	@GetMapping("/list")
	public ResponseEntity<List<CategoryDto>> categoryList(){
		List<CategoryDto> categories= new ArrayList<CategoryDto>(); 
		 categories.add(CategoryDto.builder()
                 .categoryId(1L)
                 .name("Sample Category")
                 .status(useStatus.USE)
                 .build());
		return ResponseEntity.ok(categories);
	}
	
	//카테고리상세조회
	@GetMapping("/detail/{categoryId}")
	public ResponseEntity<CategoryDto> categoryDetail(@PathVariable Long categoryId) {
        CategoryDto category = CategoryDto.builder()
                .categoryId(categoryId)
                .name("Sample Category")
                .status(useStatus.USE)
                .build();
		return ResponseEntity.ok(category);
	}
	
	//카메고리 변경
	@PutMapping("/change")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryRequestDto) {
	    URI location = ServletUriComponentsBuilder
	                    .fromCurrentRequest()
	                    .path("/detail/{categoryId}")
	                    .buildAndExpand(categoryRequestDto.getCategoryId())
	                    .toUri();


	    CategoryDto categoryResponseDto = CategoryDto.builder()
	                                                 .categoryId(categoryRequestDto.getCategoryId())
	                                                 .name(categoryRequestDto.getName())
	                                                 .status(categoryRequestDto.getStatus())
	                                                 .build();
	    return ResponseEntity.ok().location(location).body(categoryResponseDto);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable String categoryId){
		//delete로직
		  
		  return ResponseEntity.noContent().build();
	}
}
