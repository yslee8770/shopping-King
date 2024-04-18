package com.shopping.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.category.dto.CategoryDto;
import com.shopping.common.useStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CategoryDto testCategory;

    @BeforeEach
    public void setup() {
        testCategory = CategoryDto.builder()
                                  .categoryId(1L)
                                  .name("Test Category")
                                  .status(useStatus.USE)
                                  .build();
    }

    @Test
    public void testCategoryList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/list"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().json("[]")); // 빈 리스트를 반환 예상
    }

    @Test
    public void testCategoryDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/detail/{categoryId}", testCategory.getCategoryId()))
               .andExpect(MockMvcResultMatchers.status().isOk());
//               .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(testCategory.getCategoryId()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(testCategory.getName()))
//               .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(testCategory.getStatus().toString()));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/category/change")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(objectMapper.writeValueAsString(testCategory)))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.header().exists("Location")); // Location 헤더가 존재하는지 확인
    }

    @Test
    public void testDeleteCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/category/delete/{categoryId}", testCategory.getCategoryId()))
               .andExpect(MockMvcResultMatchers.status().isNoContent()); // NoContent 상태를 반환 예상
    }
}