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
import com.shopping.common.ProductStatus;
import com.shopping.product.dto.ProductDto;
import com.shopping.product.dto.ProductRequestDto;

@RestController
@RequestMapping("/product")
public class ProductController {
  @GetMapping(value = "/list")
  public ResponseEntity<List<ProductDto>> findProductList(
      @ModelAttribute ProductRequestDto productRequestDto) {
    List<ProductDto> products = new ArrayList<ProductDto>();
    products
        .add(ProductDto
            .builder()
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

  @GetMapping("/detail/{productId}")
  public ResponseEntity<ProductDto> findProductDetail(@PathVariable Long productId) {
    ProductDto product = ProductDto
        .builder()
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

  @PostMapping("/add")
  public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productRequestDto) {
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/detail/{productId}")
        .buildAndExpand(productRequestDto.getProductId())
        .toUri();
    ProductDto productResponseDto = productRequestDto;
    return ResponseEntity.created(location).body(productResponseDto);
  }

  @PutMapping("/change")
  public ResponseEntity<ProductDto> changeProduct(@RequestBody ProductDto productRequestDto) {
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/detail/{productId}")
        .buildAndExpand(productRequestDto.getProductId())
        .toUri();
    ProductDto productResponseDto = productRequestDto;
    return ResponseEntity.ok().location(location).body(productResponseDto);
  }

  @DeleteMapping("/delete/{productId}")
  public ResponseEntity<Long> deleteProduct(@PathVariable Long productId) {
    return ResponseEntity.ok(productId);
  }

}
