package com.shopping.cart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.shopping.cart.dto.CartResponseDto;
import com.shopping.cart.web.CartController;
import com.shopping.product.dto.ProductDto;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private List<CartResponseDto> cartDtos;

    @BeforeEach
    void setUp() {
        // 테스트용 데이터 초기화
        cartDtos = new ArrayList<>();
        
        // Mock 데이터 설정
        ProductDto productDto = ProductDto.builder()
            .discountPrice(10000) 
            .price(12000)
            .build();
        
        CartResponseDto cartResponseDto = CartResponseDto.builder()
            .productDto(productDto)
            .memberId(1L)
            .quantity(2) 
            .build();
        
        cartDtos.add(cartResponseDto);
    }

    @Test
    public void testCartList() throws Exception {
        mockMvc.perform(get("/cart/list/{memberId}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
//            .andExpect(jsonPath("$.[0].productDto.discountPrice", is(10000)))
//            .andExpect(jsonPath("$.[0].productDto.price", is(12000)))
//            .andExpect(jsonPath("$.[0].memberId", is(1)))
//            .andExpect(jsonPath("$.[0].quantity", is(2)))
            .andDo(print());
    }

    @Test
    public void testAddCart() throws Exception {
        mockMvc.perform(post("/cart/add/{memberId}/{productId}", 1L, 1L)
            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void testDeleteCart() throws Exception {
        mockMvc.perform(delete("/cart/delete")
            .param("productsIds", "1,2,3")
            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent())
            .andDo(print());
    }
}