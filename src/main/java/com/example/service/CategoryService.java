package com.example.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * parentCategoryを取得する
	 * @return
	 */
	public Set<String> getParentCategory(){
		List<String> TemporaryParentCategoryList = categoryRepository.findParentCategory();
		Set<String> parentCategoryList = new TreeSet<>(TemporaryParentCategoryList);
		return parentCategoryList;
	}
	
}
