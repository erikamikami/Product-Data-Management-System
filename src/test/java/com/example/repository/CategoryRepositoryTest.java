package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
	@DisplayName("parentCategoryの名前からparentIdListを取得できているか")
	public void findParentIdByParentCategoryTest() {
		// 期待値
		int expectedParentIdListSize = 109;

		// 引数
		String arg = "Men";

		// 実際
		List<Integer> actual = categoryRepository.findParentIdByParentCategory(arg);

		// 結果
		assertEquals(expectedParentIdListSize, actual.size());
	}

	@Test
	@DisplayName("parentIdからchildCategoryを取得できているか")
	public void findChildCategoryByParentCategoryIdTest() {
		// 期待値
		List<String> expected = new ArrayList<>();
		expected.add("Office supplies");
		expected.add("Daily & Travel items");
		expected.add("Pet Supplies");
		expected.add("Daily & Travel items");
		Collections.sort(expected);

		int expectedChildCategoryListSize = 4;

		// 引数 (parentNameが"Other"で検証）
		List<Integer> args = new ArrayList<>();
		args.add(37);
		args.add(223);
		args.add(268);
		args.add(283);

		// 実際
		List<String> actual = categoryRepository.findChildCategoryByParentCategoryId(args);

		// 結果
		for (int i = 0; i < expectedChildCategoryListSize; i++) {
			assertEquals(expected.get(i), actual.get(i));
		}

	}

	@Test
	@DisplayName("childCategoryの名前からchildIdListを取得できているか")
	public void findChildIdByChildCategoryTest() {
		// 期待値
		int expectedChildIdListSize = 9;

		// 引数
		String arg = "Tops";

		// 実際
		List<Integer> actual = categoryRepository.findChildIdByChildCategory(arg);

		// 結果
		assertEquals(expectedChildIdListSize, actual.size());
	}

	@Test
	@DisplayName("childIdからgrandCategoryを取得できているか")
	public void findGrandChildCategoryByChildCategoryIdTest() {
		// 期待値
		List<String> expected = new ArrayList<>();
		expected.add("Components & Parts");
		expected.add("iPad");
		expected.add("Desktops & All-In-Ones");
		expected.add("Drives  Storage & Media");
		Collections.sort(expected);
		
		int expectedGrandChildCategoryListSize = 4;

		// 引数(childNameが"Computers & Tablets"で検証）
		List<Integer> args = new ArrayList<>();
		args.add(5);
		args.add(395);
		args.add(806);
		args.add(875);

		// 実際
		List<String> actual = categoryRepository.findGrandChildCategoryByChildCategoryId(args);

		// 結果
		for (int i = 0; i < expectedGrandChildCategoryListSize; i++) {
			assertEquals(expected.get(i), actual.get(i));
		}

	}

}
