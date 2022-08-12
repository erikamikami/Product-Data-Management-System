package com.example.controller;

import java.util.HashMap;
import java.util.Map;
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
	
	@RequestMapping("/getChildCategory")
	public Set<String> getChildCategory(String parentCategory){
		Set<String> childCategoryList = categoryService.getChildCategoryList(parentCategory);
		
		return childCategoryList;
	}

}
