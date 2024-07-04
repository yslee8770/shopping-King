package com.shopping.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.dto.CategoryDto;
import com.shopping.entity.Category;
import com.shopping.enums.DeleteAt;
import com.shopping.mapper.CategoryMapper;
import com.shopping.service.CategoryService;
import com.shopping.web.CategoryController;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CategoryService categoryService;

  @Test
  @DisplayName("카테고리 이름을 포함한 목록 조회 API 테스트")
  public void testFindCategoryList_WithName() throws Exception {
    Category category = Category.builder().Id(1L).name("Test Category").deleteAt(DeleteAt.N)
        .build();
    List<Category> categories = Collections.singletonList(category);

    given(categoryService.findCategoryList("Test", 0, 10, "asc")).willReturn(categories);

    mockMvc.perform(get("/category")
            .param("categoryName", "Test")
            .param("page", "0")
            .param("size", "10")
            .param("sort", "asc"))
        .andExpect(status().isOk())
        .andDo(print())  // 응답을 출력하여 확인합니다.
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is("Test Category")));

  }

  @Test
  @DisplayName("카테고리 이름 없이 전체 목록 조회 API 테스트")
  public void testFindCategoryList_WithoutName() throws Exception {
    Category category = Category.builder().Id(1L).name("Test Category").deleteAt(DeleteAt.N)
        .build();
    List<Category> categories = Collections.singletonList(category);
    given(categoryService.findCategoryList(null, 0, 10, "asc")).willReturn(categories);

    mockMvc.perform(get("/category")
            .param("page", "0")
            .param("size", "10")
            .param("sort", "asc"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].name", is("Test Category")));
  }

  @Test
  @DisplayName("카테고리 ID로 카테고리 상세 조회 API 테스트")
  public void testFindCategoryDetail() throws Exception {
    Category category = Category.builder().Id(1L).name("Test Category").deleteAt(DeleteAt.N)
        .build();
    given(categoryService.findCategoryByCategoryId(1L)).willReturn(category);

    mockMvc.perform(get("/category/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Test Category")));
  }

  @Test
  @DisplayName("카테고리 업데이트 API 테스트")
  public void testUpdateCategory() throws Exception {
    Category category = Category.builder().Id(1L).name("Updated Name").deleteAt(DeleteAt.N)
        .build();
    CategoryDto categoryDto = CategoryMapper.categoryToCategoryDto(category);

    given(categoryService.updateCategory(any(CategoryDto.class))).willReturn(category);

    mockMvc.perform(patch("/category/{categoryId}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(categoryDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Updated Name")));
  }

  @Test
  @DisplayName("카테고리 삭제 API 테스트")
  public void testDeleteCategory() throws Exception {
    Long categoryId = 1L;
    doNothing().when(categoryService).deleteCategory(categoryId);

    mockMvc.perform(delete("/category/{categoryId}", categoryId))
        .andExpect(status().isOk())
        .andExpect(content().string(categoryId.toString()));
  }
}
