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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shopping.category.dto.CategoryDto;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	//카테고리 전체조회
	@GetMapping("/list")
	public ResponseEntity<List<CategoryDto>> categoryList(){
		List<CategoryDto> categories= new ArrayList<CategoryDto>(); 
		return ResponseEntity.ok(categories);
	}
	
	//카테고리상세조회
	@GetMapping("/detail/{categoryId}")
	public ResponseEntity<CategoryDto> categoryDetail(@PathVariable String categoryId) {
		CategoryDto category = null;
		return ResponseEntity.ok(category);
	}
	
	//카메고리 변경
	@PutMapping("/change")
	public ResponseEntity<CategoryDto> updateCategory(@ModelAttribute CategoryDto categoryRequestDto){
		  URI location = ServletUriComponentsBuilder
		            .fromCurrentRequest()
		            .path("/detail/{categoryId}")
	                .buildAndExpand(categoryRequestDto.getCategoryId())
		            .toUri();
		//update로직
		  CategoryDto categroyResponseDto = new CategoryDto();
		  
		return ResponseEntity.ok().location(location).body(categroyResponseDto);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable String categoryId){
		//delete로직
		  
		  return ResponseEntity.noContent().build();
	}
}
