package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("全parentCategoryを取得できているか")
	public void findParentCategoryTest() {
		// 期待値
		int expectedParentCategoryListSize = 1288;

		// 実際
		List<String> actual = categoryRepository.findParentCategory();

		// 結果
		assertEquals(expectedParentCategoryListSize, actual.size());
	}

	
	@Test
	@DisplayName("parentCategoryの名前からchildCategoryを取得できているか")
	public void findChildCategoryByParentCategoryTest() {
		// 引数
		String arg = "Other";
		
		// 期待値
		List<String> expected = new ArrayList<>();
		expected.add("Automotive");
		expected.add("Books");
		expected.add("Daily & Travel items");
		expected.add("Magazines");
		expected.add("Musical instruments");
		expected.add("Office supplies");
		expected.add("Other");
		expected.add("Pet Supplies");
		Collections.sort(expected);
		
		int expectedChildCategoryListSize = 8;
		
		// 実際
		List<String> actual = categoryRepository.findChildCategoryByParentCategory(arg);
		Set<String> actualSet = new TreeSet<>(actual);
		Iterator<String> iterator = actualSet.iterator();
		
		// 結果
		for (int i = 0; i < expectedChildCategoryListSize; i++) {
			assertEquals(expected.get(i), iterator.next());
		}
		
		assertEquals(expectedChildCategoryListSize, actualSet.size());
	}

	
	@Test
	@DisplayName("childCategoryの名前からgrandChildを取得できているか")
	public void findGrandChildByChildCategoryTest() {
		// 引数
		String arg = "Computers & Tablets";
		
		// 期待値
		List<String> expected = new ArrayList<>();
		expected.add("Components & Parts");
		expected.add("iPad");
		expected.add("Desktops & All-In-Ones");
		expected.add("Drives  Storage & Media");
		Collections.sort(expected);
		
		int expectedGrandChildCategoryListSize = 4;
		
		// 実際
		List<String> actual = categoryRepository.findGrandChildByChildCategory(arg);

		// 結果
		for (int i = 0; i < expectedGrandChildCategoryListSize; i++) {
			assertEquals(expected.get(i), actual.get(i));
		}

		
	}

}
