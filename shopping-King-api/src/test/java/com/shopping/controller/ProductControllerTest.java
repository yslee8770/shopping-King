package com.shopping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.shopping.dto.ProductRequestDto;
import com.shopping.dto.ProductResponseDto;
import com.shopping.enums.DeleteAt;
import com.shopping.mapper.ProductMapper;
import com.shopping.service.ProductService;
import com.shopping.web.ProductController;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
  }

  @Test
  public void testFindProductList() {
    ProductResponseDto productResponseDto = ProductResponseDto.builder()
        .productId(1L)
        .productName("Test Product")
        .stockQuantity(10)
        .salesRate(5)
        .category("Test Category")
        .price(100)
        .discountRate(10)
        .discountPrice(90)
        .registDt(LocalDateTime.now())
        .deleteAt(DeleteAt.N)
        .description("Test Description")
        .build();

    when(productService.findCategoryList(anyLong())).thenReturn(
        Collections.singletonList(ProductMapper.productRequestDtoToProduct(
            ProductRequestDto.builder()
                .productNm("Test Product")
                .productPrice(100)
                .stockAmount(10)
                .salesRate(5)
                .discountRate(10)
                .discountPrice(90)
                .registDt(LocalDateTime.now())
                .description("Test Description")
                .deleteAt(DeleteAt.N)
                .categoryId(1L)
                .build(), null
        )));

    ResponseEntity<List<ProductResponseDto>> response = productController.findProductList(1L);
    assertEquals(1, response.getBody().size());
    assertEquals("Test Product", response.getBody().get(0).getProductName());
  }

  @Test
  public void testFindProductDetail() {
    ProductResponseDto productResponseDto = ProductResponseDto.builder()
        .productId(1L)
        .productName("Test Product")
        .stockQuantity(10)
        .salesRate(5)
        .category("Test Category")
        .price(100)
        .discountRate(10)
        .discountPrice(90)
        .registDt(LocalDateTime.now())
        .deleteAt(DeleteAt.N)
        .description("Test Description")
        .build();

    when(productService.findProudctByProductId(anyLong())).thenReturn(
        ProductMapper.productRequestDtoToProduct(
            ProductRequestDto.builder()
                .productNm("Test Product")
                .productPrice(100)
                .stockAmount(10)
                .salesRate(5)
                .discountRate(10)
                .discountPrice(90)
                .registDt(LocalDateTime.now())
                .description("Test Description")
                .deleteAt(DeleteAt.N)
                .categoryId(1L)
                .build(), null
        ));

    ResponseEntity<ProductResponseDto> response = productController.findProductDetail(1L);
    assertEquals("Test Product", response.getBody().getProductName());
  }

  @Test
  public void testAddProduct() {
    ProductRequestDto requestDto = ProductRequestDto.builder()
        .productNm("Test Product")
        .productPrice(100)
        .stockAmount(10)
        .salesRate(5)
        .discountRate(10)
        .discountPrice(90)
        .registDt(LocalDateTime.now())
        .description("Test Description")
        .deleteAt(DeleteAt.N)
        .categoryId(1L)
        .build();

    when(productService.changeProduct(any(ProductRequestDto.class))).thenReturn(
        ProductMapper.productRequestDtoToProduct(requestDto, null));

    ResponseEntity<ProductResponseDto> response = productController.addProduct(requestDto);
    assertEquals("Test Product", response.getBody().getProductName());
  }

  @Test
  public void testChangeProduct() {
    ProductRequestDto requestDto = ProductRequestDto.builder()
        .productNm("Test Product")
        .productPrice(100)
        .stockAmount(10)
        .salesRate(5)
        .discountRate(10)
        .discountPrice(90)
        .registDt(LocalDateTime.now())
        .description("Test Description")
        .deleteAt(DeleteAt.N)
        .categoryId(1L)
        .build();

    when(productService.changeProduct(any(ProductRequestDto.class))).thenReturn(
        ProductMapper.productRequestDtoToProduct(requestDto, null));

    ResponseEntity<ProductResponseDto> response = productController.changeProduct(requestDto);
    assertEquals("Test Product", response.getBody().getProductName());
  }

  @Test
  public void testDeleteProduct() {
    ResponseEntity<Long> response = productController.deleteProduct(1L);
    assertEquals(1L, response.getBody());
  }
}
