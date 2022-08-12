package com.example.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * parentCategoryを取得する
	 * @return
	 */
	public Set<String> getParentCategoryList(){
		List<String> TemporaryParentCategoryList = categoryRepository.findParentCategory();
		Set<String> parentCategoryList = new TreeSet<>(TemporaryParentCategoryList);
		return parentCategoryList;
	}
	
	/**
	 * parentCategoryからchildCategoryを取得する
	 * @param parentCategory
	 * @return
	 */
	@Transactional
	public Set<String> getChildCategoryList(String parentCategory){
		List<Integer> parentIdList = categoryRepository.findParentIdByParentCategory(parentCategory);
		List<String> TemporaryChildCategoryList = categoryRepository.findChildCategoryByParentCategory(parentIdList);
		Set<String> childCategoryList = new TreeSet<>(TemporaryChildCategoryList);
		
		return childCategoryList;
		
	}
	
}
