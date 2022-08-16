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
		TemporaryParentCategoryList.add(0, "");
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
		List<String> childCategoryList = categoryRepository.findChildCategoryByParentCategoryId(parentIdList);
		Set<String> childCategorySet = new TreeSet<>(childCategoryList);
		
		return childCategorySet;
		
	}
	
	/**
	 * childCategoryからgrandCategoryを取得する
	 * @param childCategory
	 * @return
	 */
	public Set<String> getGrandCategoryList(String childCategory){
		List<Integer> childIdList = categoryRepository.findChildIdByChildCategory(childCategory);
		List<String> grandCategoryList = categoryRepository.findGrandChildCategoryByChildCategoryId(childIdList);
		Set<String> grandCategorySet = new TreeSet<>(grandCategoryList);
		
		return grandCategorySet;
	}
	
	/**
	 * childCategoryを取得
	 * @return
	 */
	public Set<String> findChildCategory(){
		List<String> temporaryChildCategoryList = categoryRepository.findChildCategory();
		temporaryChildCategoryList.add(0, "");
		Set<String> childCategorySet = new TreeSet<>(temporaryChildCategoryList);
		
		return childCategorySet;
	}
	
	/**
	 * grandChildを取得
	 * @return
	 */
	public Set<String> findGrandChild(){
		List<String> temporaryGrandCategoryList = categoryRepository.findGrandChild();
		temporaryGrandCategoryList.add(0, "");
		Set<String> grandCategorySet = new TreeSet<>(temporaryGrandCategoryList);
		
		return grandCategorySet;
	}
	
}
