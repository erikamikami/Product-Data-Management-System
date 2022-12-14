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

import com.example.entity.Item;
import com.example.entity.ItemSearch;
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
		
		ItemSearch itemSearch = new ItemSearch();
		
		when(itemRepository.countItems(itemSearch)).thenReturn(593381);
		
		// 実際
		Pagination testMaterial = new Pagination();
		testMaterial.setPage(5);
		Pagination actual = itemService.paging(itemSearch, testMaterial);
		
		// 結果
		assertEquals(expected.getPage(), actual.getPage());
		assertEquals(expected.getDisplaysPerPage(), actual.getDisplaysPerPage());
		assertEquals(expected.getTotalDisplays(), actual.getTotalDisplays());
		assertEquals(totalPage, actual.getTotalPage());
		
	}
	
	@Test
	@DisplayName("itemを登録できているか")
	public void registerTest() {
		// 戻り値作成
		int id = 1482534;
		when(itemRepository.findMaxId()).thenReturn(id);
		
		// 新規登録するitem作成
		Item item = new Item(0, "商品名", 1, "商品カテゴリ", "ブランド名", 52.0, 0, "商品説明");
		
		// 実際
		itemService.register(item);
	}

}
