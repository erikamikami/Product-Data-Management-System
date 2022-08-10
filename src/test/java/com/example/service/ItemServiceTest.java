package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

import com.example.pagination.Pagination;
import com.example.repository.ItemRepository;

@SpringBootTest
class ItemServiceTest {
	
	private AutoCloseable autoCloseable;
	
	@Mock
	private ItemRepository itemRepository;
	
	@InjectMocks
	private ItemService itemService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(itemService);
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	@DisplayName("ページング処理を行えているかどうか")
	public void test() {
		// 期待値
		Pagination expected = new Pagination();
		expected.setPage(5); // 5ページ目を指定（１ページあたりの表示件数はデフォルトのまま３０）
		expected.setTotalDisplays(593381);
		int totalPage = expected.getTotalDisplays()/expected.getDisplaysPerPage();
		totalPage++;
		
		when(itemRepository.countAllItems()).thenReturn(593381);
		
		// 実際
		Pagination testMaterial = new Pagination();
		testMaterial.setPage(5);
		Pagination actual = itemService.paging(testMaterial);
		
		// 結果
		assertEquals(expected.getPage(), actual.getPage());
		assertEquals(expected.getDisplaysPerPage(), actual.getDisplaysPerPage());
		assertEquals(expected.getTotalDisplays(), actual.getTotalDisplays());
		assertEquals(totalPage, actual.getTotalPage());
		
	}

}
