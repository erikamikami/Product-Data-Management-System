package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryRepository {
	
	/**
	 * parentCategoryを取得
	 * @return
	 */
	public List<String> findParentCategory();
	
	/**
	 * parentCategoryの名前からchildCategoryを取得
	 * @param parentCategory
	 * @return
	 */
	public List<String> findChildCategoryByParentCategory(String parentCategory);
	
	/**
	 * childCategoryの名前からgrandChildを取得
	 * @param childCayegory
	 * @return
	 */
	public List<String> findGrandChildByChildCategory(String childCayegory);
	
	/**
	 * childCategoryを取得
	 * @return
	 */
	public List<String> findChildCategory();
	
	/**
	 * grandChildを取得
	 * @return
	 */
	public List<String> findGrandChild();

}
