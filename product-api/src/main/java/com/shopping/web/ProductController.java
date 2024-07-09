package com.shopping.web;

import com.shopping.dto.ProductRequestDTO;
import com.shopping.dto.ProductResponseDTO;
import com.shopping.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
    return ResponseEntity.ok(productService.createProduct(productRequestDTO));
  }

  @PutMapping("/{productId}")
  public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO productRequestDTO) {
    return ResponseEntity.ok(productService.updateProduct(productId, productRequestDTO));
  }

  @DeleteMapping("/{productId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{productId}")
  public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId) {
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }
}
