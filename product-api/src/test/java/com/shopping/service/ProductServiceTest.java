package com.shopping.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shopping.dto.ProductRequestDTO;
import com.shopping.dto.ProductResponseDTO;
import com.shopping.entity.Category;
import com.shopping.entity.Product;
import com.shopping.exception.CustomException;
import com.shopping.repository.CategoryRepository;
import com.shopping.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private ProductService productService;

  public ProductServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void createProduct_success() {
    Category category = Category.builder().id(1L).name("Electronics").build();
    Product product = Product.builder().id(1L).name("Laptop").category(category).build();
    when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
    when(productRepository.save(any(Product.class))).thenReturn(product);

    ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().name("Laptop").description("A powerful laptop").price(1000.0).categoryId(1L).build();
    ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);

    verify(productRepository, times(1)).save(any(Product.class));
    verify(categoryRepository, times(1)).findById(anyLong());
  }

  @Test
  public void updateProduct_productNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

    ProductRequestDTO productRequestDTO = ProductRequestDTO.builder().name("Laptop").description("A powerful laptop").price(1000.0).categoryId(1L).build();
    assertThrows(CustomException.class, () -> {
      productService.updateProduct(1L, productRequestDTO);
    });

    verify(productRepository, times(1)).findById(anyLong());
  }

  @Test
  public void deleteProduct_productNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(CustomException.class, () -> {
      productService.deleteProduct(1L);
    });

    verify(productRepository, times(1)).findById(anyLong());
  }

  @Test
  public void getProductById_productNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(CustomException.class, () -> {
      productService.getProductById(1L);
    });

    verify(productRepository, times(1)).findById(anyLong());
  }

  @Test
  public void getAllProducts_success() {
    Category category = Category.builder().id(1L).name("Electronics").build();
    when(productRepository.findAll()).thenReturn(List.of(
        Product.builder().id(1L).name("Laptop").category(category).build(),
        Product.builder().id(2L).name("Tablet").category(category).build()
    ));

    List<ProductResponseDTO> products = productService.getAllProducts();

    verify(productRepository, times(1)).findAll();
  }
}
