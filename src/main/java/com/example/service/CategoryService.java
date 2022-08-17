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
	public Set<String> getParentCategory(){
		List<String> parentCategoryList = categoryRepository.findParentCategory();
		parentCategoryList.add(0, "");
		Set<String> parentCategorySet = new TreeSet<>(parentCategoryList);
		return parentCategorySet;
	}
	
	/**
	 * parentCategoryからchildCategoryを取得する
	 * @param parentCategory
	 * @return
	 */
	@Transactional
	public Set<String> getChildCategory(String parentCategory){
		List<String> childCategoryList = categoryRepository.findChildCategoryByParentCategory(parentCategory);
		childCategoryList.add(0, "");
		Set<String> childCategorySet = new TreeSet<>(childCategoryList);
		
		return childCategorySet;
		
	}
	
	/**
	 * childCategoryからgrandCategoryを取得する
	 * @param childCategory
	 * @return
	 */
	public Set<String> getGrandChild(String childCategory){
		List<String> grandCategoryList = categoryRepository.findGrandChildByChildCategory(childCategory);
		grandCategoryList.add(0, "");
		Set<String> grandCategorySet = new TreeSet<>(grandCategoryList);
		
		return grandCategorySet;
	}
	
	/**
	 * childCategoryを取得
	 * @return
	 */
	public Set<String> getChildCategory(){
		List<String> childCategoryList = categoryRepository.findChildCategory();
		childCategoryList.add(0, "");
		Set<String> childCategorySet = new TreeSet<>(childCategoryList);
		
		return childCategorySet;
	}
	
	/**
	 * grandChildを取得
	 * @return
	 */
	public Set<String> getGrandChild(){
		List<String> grandCategoryList = categoryRepository.findGrandChild();
		grandCategoryList.add(0, "");
		Set<String> grandCategorySet = new TreeSet<>(grandCategoryList);
		
		return grandCategorySet;
	}
	
}
