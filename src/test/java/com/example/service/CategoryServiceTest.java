package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.repository.CategoryRepository;

@SpringBootTest
class CategoryServiceTest {
	
	private AutoCloseable autoCloseable;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(categoryService);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	@DisplayName("parentCategoryを取得してListからSetに変換できているか")
	public void getParentCategoryListTest() {
		// 期待値
		Set<String> expected = new TreeSet<>();
		expected.add("Men");
		expected.add("Electronics");
		expected.add("Women");
		expected.add("Women");
		expected.add("Home");
		expected.add("Sports & Outdoors");
		
		List<String> parentCategoryList = new ArrayList<>();
		parentCategoryList.add("Men");
		parentCategoryList.add("Electronics");
		parentCategoryList.add("Women");
		parentCategoryList.add("Home");
		parentCategoryList.add("Women");
		parentCategoryList.add("Sports & Outdoors");
		
		when(categoryRepository.findParentCategory()).thenReturn(parentCategoryList);
		
		// 実際
		Set<String> actual = categoryService.getParentCategoryList();
		
		// 結果
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("parentCategoryからchildCategoryを取得し、ListからSetに変換ができているか")
	public void getChildCategoryListTest() {
		// 期待値
		Set<String> expected = new TreeSet<>();
		expected.add("Tops & Blouses");
		expected.add("Jewelry");
		expected.add("Other");
		expected.add("Swimwear");
		expected.add("Dresses");
		
		String parentCategory = "Women";
		
		List<Integer> parentIdList = new ArrayList<>();
		parentIdList.add(7);
		parentIdList.add(13);
		parentIdList.add(16);
		parentIdList.add(19);
		parentIdList.add(34);
		
		
		when(categoryRepository.findParentIdByParentCategory(parentCategory)).thenReturn(parentIdList);
		
		List<String> childCategoryList = new ArrayList<>();
		childCategoryList.add("Tops & Blouses");
		childCategoryList.add("Jewelry");
		childCategoryList.add("Other");
		childCategoryList.add("Swimwear");
		childCategoryList.add("Dresses");
		
		when(categoryRepository.findChildCategoryByParentCategoryId(parentIdList)).thenReturn(childCategoryList);
		
		// 実際
		Set<String> actual = categoryService.getChildCategoryList(parentCategory);
		
		// 結果
		assertEquals(expected, actual);
	}

}
