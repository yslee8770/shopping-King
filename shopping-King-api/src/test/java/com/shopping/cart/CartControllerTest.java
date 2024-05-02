package com.shopping.cart;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.cart.dto.CartResponseDto;
import com.shopping.cart.web.CartController;
import com.shopping.product.dto.ProductDto;

@WebMvcTest(CartController.class)
public class CartControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private List<CartResponseDto> cartDtos;

  @BeforeEach
  void setUp() {
    cartDtos = new ArrayList<>();

    ProductDto productDto = ProductDto.builder().discountPrice(10000).price(12000).build();

    CartResponseDto cartResponseDto =
        CartResponseDto.builder().productDto(productDto).memberId(1L).quantity(2).build();

    cartDtos.add(cartResponseDto);
  }

  @Test
  public void testCartList() throws Exception {
    mockMvc
        .perform(get("/cart/list/{memberId}", 1L).contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.[0].productDto.productName", is("Sample Product")))
        .andExpect(jsonPath("$.[0].productDto.productId", is(1)))
        .andExpect(jsonPath("$.[0].productDto.stockQuantity", is(10)))
        .andExpect(jsonPath("$.[0].productDto.salesRate", is(5)))
        .andExpect(jsonPath("$.[0].productDto.category", is("Sample Category")))
        .andExpect(jsonPath("$.[0].productDto.price", is(12000)))
        .andExpect(jsonPath("$.[0].productDto.discountRate", is(20)))
        .andExpect(jsonPath("$.[0].productDto.registrationDate").exists())
        .andExpect(jsonPath("$.[0].productDto.statusCode", is("SALE")))
        .andExpect(jsonPath("$.[0].productDto.description", is("Sample Description")))
        .andExpect(jsonPath("$.[0].cartId", is(1)))
        .andExpect(jsonPath("$.[0].memberId", is(1)))
        .andExpect(jsonPath("$.[0].quantity", is(2)))
        .andExpect(jsonPath("$.[0].price", is(24000)))
        .andDo(print());
  }

  @Test
  public void testAddCart() throws Exception {
    mockMvc
        .perform(post("/cart/add/{memberId}/{productId}", 1L, 1L)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].productDto.productName", is("Sample Product")))
        .andExpect(jsonPath("$.[0].productDto.productId", is(1)))
        .andExpect(jsonPath("$.[0].productDto.stockQuantity", is(10)))
        .andExpect(jsonPath("$.[0].productDto.salesRate", is(5)))
        .andExpect(jsonPath("$.[0].productDto.category", is("Sample Category")))
        .andExpect(jsonPath("$.[0].productDto.price", is(12000)))
        .andExpect(jsonPath("$.[0].productDto.discountRate", is(20)))
        .andExpect(jsonPath("$.[0].productDto.registrationDate").exists())
        .andExpect(jsonPath("$.[0].productDto.statusCode", is("SALE")))
        .andExpect(jsonPath("$.[0].productDto.description", is("Sample Description")))
        .andExpect(jsonPath("$.[0].cartId", is(1)))
        .andExpect(jsonPath("$.[0].memberId", is(1)))
        .andExpect(jsonPath("$.[0].quantity", is(2)))
        .andExpect(jsonPath("$.[0].price", is(24000)))
        .andDo(print());
  }

  @Test
  public void testDeleteCart() throws Exception {
    List<Long> expectedProductIds = Arrays.asList(1L, 2L, 3L);

    mockMvc
        .perform(delete("/cart/delete")
            .param("productsIds", "1,2,3")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedProductIds)))
        .andDo(print());
  }
}
