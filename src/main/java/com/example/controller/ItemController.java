package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.pagination.Pagination;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@ModelAttribute
	public Pagination setUpToPagination() {
		return new Pagination();
	}
	
	@Autowired
	private ItemService itemService;
	
	/** item全件リストを表示
	 * @param pagination
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String toShowItemList(Pagination pagination, Model model) {
		List<Item> itemList = itemService.getAllItems(pagination);
		pagination = itemService.paging(pagination);
		
		model.addAttribute("itemList", itemList);
		model.addAttribute("pagination", pagination);
		
		return "list";
	}
	

}
