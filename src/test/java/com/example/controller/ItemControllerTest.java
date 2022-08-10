package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

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

import com.example.entity.Item;
import com.example.pagination.Pagination;
import com.example.service.ItemService;

@SpringBootTest
class ItemControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private ItemService itemService;
	
	@InjectMocks
	private ItemController itemController;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("item全件リストを表示できているか")
	public void toShowItemListTest() throws Exception {
		// 期待値作成
		Pagination pagination = new Pagination();
		pagination.setPage(1);
		pagination.setDisplaysPerPage(2);
		pagination.setTotalDisplays(1482535);
		pagination.setTotalPage(pagination.getDisplaysPerPage(), pagination.getTotalDisplays());
		
		List<Item> itemList = new ArrayList<>() {
			{
				add(new Item(0, "MLB Cincinnati Reds T Shirt Size XL", 3, "Men/Tops/T-shirts0", null, 10.0, 1, "No description yet"));
				add(new Item(1, "Razer BlackWidow Chroma Keyboard", 3, "Electronics/Computers & Tablets/Components & Parts", "Razer", 52.0, 0, "This keyboard is in great condition and works like it came out of the box. All of the ports are tested and work perfectly. The lights are customizable via the Razer Synapse app on your PC."));
			}
		};
		
		when(itemService.getAllItems(pagination)).thenReturn(itemList);
		when(itemService.paging(pagination)).thenReturn(pagination);
		
		// 引数作成
		Pagination argument = new Pagination();
		argument.setPage(1);
		argument.setDisplaysPerPage(2);
		
		MvcResult result = mockMvc.perform(post("/item/list").flashAttr("pagination", argument))
									.andExpect(status().is2xxSuccessful())
									.andExpect(view().name("list"))
									.andExpect(model().attributeExists("itemList"))
									.andExpect(model().attributeExists("pagination"))
									.andReturn();
		
		List<Item> actualItemList = (List<Item>) result.getModelAndView().getModel().get("itemList");
		Pagination actualPagination = (Pagination) result.getModelAndView().getModel().get("pagination");
		
		assertEquals(actualItemList, itemList);
		assertEquals(pagination, actualPagination);
	}

}
