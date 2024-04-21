package com.shopping.product.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shopping.product.dto.ProductRequestDto;
import com.shopping.common.ProductStatus;
import com.shopping.product.dto.ProductDto;

@RestController
@RequestMapping("/product")
public class ProductController {
	//상품조회
	@GetMapping(value =  "/list" )
	 public ResponseEntity<List<ProductDto>> productList(@ModelAttribute ProductRequestDto productRequestDto) {
		List<ProductDto> products = new ArrayList<ProductDto>();
		 products.add(ProductDto.builder()
	                .productName("Test Product")
	                .productId(1L)
	                .stockQuantity(100)
	                .salesRate(50)
	                .category("Test Category")
	                .price(10000)
	                .discountRate(10)
	                .statusCode(ProductStatus.SALE)
	                .description("Test Description")
	                .build());
		return ResponseEntity.ok(products);
	}
	//상품상세조회
	@GetMapping("/detail/{productId}")
	public ResponseEntity<ProductDto> productDetail(@PathVariable Long productId) {
		  ProductDto product = ProductDto.builder()
	                .productName("Test Product")
	                .productId(productId)
	                .stockQuantity(100)
	                .salesRate(50)
	                .category("Test Category")
	                .price(10000)
	                .discountRate(10)
	                .statusCode(ProductStatus.SALE)
	                .description("Test Description")
	                .build();
		return ResponseEntity.ok(product);
	}
	
	//상품등록
	@PostMapping("/add")
	public ResponseEntity<ProductDto> insertProduct(@RequestBody  ProductDto productRequestDto){
		  URI location = ServletUriComponentsBuilder
		            .fromCurrentRequest()
		            .path("/detail/{productId}")
	                .buildAndExpand(productRequestDto.getProductId())
		            .toUri();
		  //inset로직
		  ProductDto productResponseDto = productRequestDto;

		  return ResponseEntity.created(location).body(productResponseDto);
	}
	//상품 변경
	@PutMapping("/change")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody  ProductDto productRequestDto){
		  URI location = ServletUriComponentsBuilder
		            .fromCurrentRequest()
		            .path("/detail/{productId}")
	                .buildAndExpand(productRequestDto.getProductId())
		            .toUri();
		//update로직
		  ProductDto productResponseDto = productRequestDto;
		  
		return ResponseEntity.ok().location(location).body(productResponseDto);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable String productId){
		//delete로직
		  
		  return ResponseEntity.noContent().build();
	}

}
