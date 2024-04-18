package com.shopping.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.product.dto.ProductDto;
import com.shopping.common.ProductStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDto testProduct;

    @BeforeEach
    public void setup() {
        testProduct = ProductDto.builder()
                                .productId(1L)
                                .productName("Test Product")
                                .stockQuantity(100)
                                .salesRate(50)
                                .category("Test Category")
                                .price(10000)
                                .discountRate(10)
                                .statusCode(ProductStatus.SALE)
                                .description("Test Description")
                                .build();
    }

    @Test
    public void testProductList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/list"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().json("[{\"productName\":\"test\"}]"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testProductDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/detail/{productId}", testProduct.getProductId()))
//               .andExpect(MockMvcResultMatchers.status().isOk())
//               .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(testProduct.getProductId()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value(testProduct.getProductName()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.stockQuantity").value(testProduct.getStockQuantity()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.salesRate").value(testProduct.getSalesRate()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(testProduct.getCategory()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(testProduct.getPrice()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.discountRate").value(testProduct.getDiscountRate()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(testProduct.getStatusCode().toString()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(testProduct.getDescription()))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testInsertProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/add")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(objectMapper.writeValueAsString(testProduct)))
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(MockMvcResultMatchers.header().exists("Location"))
//               .andExpect(MockMvcResultMatchers.content().json("{\"productId\":1}"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/product/change")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(objectMapper.writeValueAsString(testProduct)))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.header().exists("Location"))
//               .andExpect(MockMvcResultMatchers.content().json("{\"productId\":1}"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete/{productId}", testProduct.getProductId()))
               .andExpect(MockMvcResultMatchers.status().isNoContent())
               .andDo(MockMvcResultHandlers.print());
    }
}
