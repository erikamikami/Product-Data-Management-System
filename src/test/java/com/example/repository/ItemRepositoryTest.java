package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

import com.example.entity.Item;
import com.example.form.ItemSearchForm;
import com.example.pagination.Pagination;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository itemRepository;

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
	@DisplayName("itemテーブルの総レコード数を取得できているか")
	public void countAllItemsTest() {
		// 期待値
		int expected = 1482535;
		
		// 実際
		int actual = itemRepository.countAllItems();
		
		// 結果
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("itemを全て取得し、ページングができているか")
	public void findAllTest() {
		// 引数のpaginationを作成
		Pagination pagination = new Pagination();
		pagination.setPage(4); // 4ページ目に設定
		pagination.setDisplaysPerPage(5); // 1ページあたりの表示数を5とする
		
		// 期待値作成
		List<Item> expected = new ArrayList<>();
		List<String> nameList = new ArrayList<>() {
			{
				add("Sephora tarte birthday gift");
				add("Glitter Eyeshadow");
				add("New: Baby K'tan active baby carrier");
				add("Too Faced Limited \"Merry Macaroons\"");
				add("Cream/ Beige Front Cross Shirt");
			}
		};
		
		for(String name : nameList) {
			Item item = new Item();
			item.setName(name);
			expected.add(item);
		}
		
		// 実際
		List<Item> actual = itemRepository.findAll(pagination);
		
		// 結果
		for(int i=0; i<=4; i++) {
			assertEquals(expected.get(i).getName(), actual.get(i).getName());
		}
	}
	
	@Test
	@DisplayName("idからitemを取得できているか")
	public void findById() {
		// 期待値
		Item expected = new Item(2345, "Overwatch origins edition xbox one", 3, "Electronics/Video Games & Consoles/Games", "Xbox", 29.0, 0, "Tested and working");
		
		// 引数のid
		int arg = 2345;
		
		// 実際
		Item actual = itemRepository.findById(arg);
		
		// 結果
		assertEquals(expected.toString(), actual.toString());
	}
	@Test
	@DisplayName("idの最大値を取得できているか")
	public void findMaxIdTest() {
		// 期待値
		int expected = 1482534;
		
		// 実際
		int actual = itemRepository.findMaxId();
		
		// 結果
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("itemを1件登録できているか")
	public void insertTest() {
		// 登録前の事前確認
		int beforeInsertCntExpected = 1482535; //登録前の登録件数期待値
		int beforeInsertCntActual = itemRepository.countAllItems();
		assertEquals(beforeInsertCntExpected, beforeInsertCntActual);
		
		// 登録対象itemオブジェクト作成
		int id = itemRepository.findMaxId();
		Item item = new Item(++id, "商品名", 1, "商品カテゴリ", "ブランド名", 52.0, 0, "商品説明");
		
		// 実際に登録を行う
		itemRepository.insert(item);
		
		// 登録後 件数確認
		int afterInsertCntExpected = 1482536; //登録前の登録件数期待値
		int afterInsertCntActual = itemRepository.countAllItems();
		assertEquals(afterInsertCntExpected, afterInsertCntActual);
		
	}
	
	@Test
	@DisplayName("itemを更新できているか")
	public void updateTetst() {
		// 更新対象item
		Item targetItem = new Item(6, "Acacia pacific tides santorini top", 3, "Women/Swimwear/Two-Piece", "Acacia Swimwear", 64.0, 0, "Size small but straps slightly shortened to fit xs, besides that, perfect condition");
		
		// 更新前確認
		Item beforeUpdateActual = itemRepository.findById(targetItem.getId());
		assertEquals(targetItem.toString(), beforeUpdateActual.toString());
		
		// 更新準備(condition, category, itemDescriptionを変更する)
		targetItem.setCondition(1);
		targetItem.setCategory("Beauty/Skin Care/Face");
		targetItem.setItemDescription("New unused and authentic. Caudalie beauty elixir mist. 1 oz");
		
		// 更新
		itemRepository.update(targetItem);
		
		// 更新後確認(更新箇所が更新されているか)
		Item afterUpdateActual = itemRepository.findById(targetItem.getId());
		assertEquals(targetItem.getCondition(), afterUpdateActual.getCondition());
		assertEquals(targetItem.getCategory(), afterUpdateActual.getCategory());
		assertEquals(targetItem.getItemDescription(), afterUpdateActual.getItemDescription());
		
		// 更新後確認（更新箇所以外変更されていないか
		assertEquals(afterUpdateActual.getId(), beforeUpdateActual.getId());
		assertEquals(afterUpdateActual.getName(), beforeUpdateActual.getName());
		assertEquals(afterUpdateActual.getBrandName(), beforeUpdateActual.getBrandName());
		assertEquals(afterUpdateActual.getPrice(), beforeUpdateActual.getPrice());
		assertEquals(afterUpdateActual.getShipping(), beforeUpdateActual.getShipping());
	}
	
	@Test
	@DisplayName("itemを検索できているか")
	public void searchTest() {
		// 期待値
		int expectedListSize = 15108;
		Item expectedConteinItem = new Item(78, "Trump Shirt", 3, "Men/Tops/T-shirts", null, 9.0, 0, "Worn once; will be washed before sent");
		
		// 検索フォーム
		ItemSearchForm itemForm = new ItemSearchForm();
		itemForm.setParentCategory("Men");
		itemForm.setChildCategory("Tops");
		itemForm.setGrandChild("T-shirts");
		
		// 実際
		List<Item> actual = itemRepository.search(itemForm);
		
		// テスト
		assertEquals(expectedListSize, actual.size());
		
	}
	
	@Test
	@DisplayName("itemをnameのみで検索できているか")
	public void searchTest2() {
		// 期待値
		int expectedListSize = 788;
		
		// 検索フォーム
		ItemSearchForm itemForm = new ItemSearchForm();
		itemForm.setName("Lebron");
		
		// 実際
		List<Item> actual = itemRepository.search(itemForm);
		
		// テスト
		assertEquals(expectedListSize, actual.size());
		
	}
	
	@Test
	@DisplayName("itemをBrandNameのみで検索できているか")
	public void searchTestByBrandName() {
		// 期待値
		int expectedListSize = 54147;
		
		// 検索フォーム
		ItemSearchForm itemForm = new ItemSearchForm();
		itemForm.setBrandName("Nike");
		
		// 実際
		List<Item> actual = itemRepository.search(itemForm);
		
		// テスト
		assertEquals(expectedListSize, actual.size());;
		
	}

}
