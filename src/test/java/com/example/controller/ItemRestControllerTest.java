package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.service.CategoryService;

@SpringBootTest
class ItemRestControllerTest {

	private MockMvc mockMvc;

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private ItemRestController itemRestController;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(itemRestController).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("引数のparentCategoryに結びついたchildCategoryを提示できているか")
	public void getChildCategoryTest() throws Exception {
		// 引数のparentCategoryを生成
		String parentCategory = "Men";
		
		// 引数のparentCategoryに結びついたchildCategory
		Set<String> childCategoryList = new TreeSet<>();
		childCategoryList.add("Tops");
		childCategoryList.add("Shoes");
		childCategoryList.add("Athletic Apparel");
		
		// レスポンス期待値
		String json = "[\"Athletic Apparel\",\"Shoes\",\"Tops\"]";
		
		when(categoryService.getChildCategoryList(parentCategory)).thenReturn(childCategoryList);
		
		MvcResult result = mockMvc.perform(
				post("/rest/item/getChildCategory")
				.param("parentCategory", parentCategory)
				.characterEncoding("UTF-8")
				)
					.andDo(print())
					.andExpect(status().is2xxSuccessful())
					.andReturn()
					;
		
		String resuponse = result.getResponse().getContentAsString();
		
		assertEquals(json, resuponse);
	}
	
	@Test
	@DisplayName("childCategoryを選択後、それに結びついたbrandCategoryを提示できているか")
	public void getGrandcategoryTest() throws Exception {
		// 引数のchildCategory作成
		String childCategory = "Tops";
		
		// 引数のchildCategoryに結びついたGrandCategory作成
		Set<String> grandCategoryList = new TreeSet<>();
		grandCategoryList.add("T-shirts");
		grandCategoryList.add("Button-Front");
		grandCategoryList.add("Polo  Rugby");
		
		// レスポンス期待値
		String json = "[\"Button-Front\",\"Polo  Rugby\",\"T-shirts\"]";
		
		when(categoryService.getGrandCategoryList(childCategory)).thenReturn(grandCategoryList);
		
		MvcResult result = mockMvc.perform(
				post("/rest/item/getGrandCategory")
				.param("childCategory", childCategory)
				.characterEncoding("UTF-8")
				)
					.andExpect(status().is2xxSuccessful())
					.andReturn()
					;
		
		String response = result.getResponse().getContentAsString();
		
		assertEquals(json, response);
		
	}

}
