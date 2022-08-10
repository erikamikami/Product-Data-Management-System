package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.pagination.Pagination;
import com.example.service.CategoryService;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@ModelAttribute
	public Pagination setUpToPagination() {
		return new Pagination();
	}
	
	@ModelAttribute
	public ItemForm setUpToItemForm() {
		return new ItemForm();
	}
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
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
	
	/**
	 * item新規追加画面へ遷移
	 * @return
	 */
	@RequestMapping("/add")
	public String toShowItemAddForm(Model model) {
		Set<String> parentCategoryList = categoryService.getParentCategory();
		model.addAttribute("parentCategoryList", parentCategoryList);
		return "add";
	}

}
