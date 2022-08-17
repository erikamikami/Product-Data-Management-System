package com.example.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.CategoryService;

@RestController
@RequestMapping("/rest/item")
public class ItemRestController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * parentCategoryを選択後、それに結びついたchildCategoryを提示
	 * @param parentCategory
	 * @return childCategory
	 */
	@RequestMapping("/getChildCategory")
	public Set<String> getChildCategory(String parentCategory){
		Set<String> childCategoryList = categoryService.getChildCategory(parentCategory);
		
		return childCategoryList;
	}
	
	/**
	 * childCategoryを選択後、それに結びついたbrandCategoryを提示
	 * @param childCategory
	 * @return brandCategory
	 */
	@RequestMapping("/getGrandCategory")
	public Set<String> getGrandcategory(String childCategory){
		Set<String> grandCategoryList = categoryService.getGrandChild(childCategory);
		
		return grandCategoryList;
	}

}
