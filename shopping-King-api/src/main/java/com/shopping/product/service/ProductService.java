package com.shopping.product.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.shopping.product.dto.ProductDto;
import com.shopping.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<ProductDto> findAllProducts() {
    return null;
  }

  public ProductDto findProduct() {
    return null;
  }

}
