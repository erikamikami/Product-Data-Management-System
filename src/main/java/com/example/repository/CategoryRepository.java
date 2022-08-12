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
	 * parentCategoryの名前からparentIdListを取得
	 * @return
	 */
	public List<Integer> findParentIdByParentCategory(String parentCategory);
	
	/**
	 * parentIdからchildCategoryを取得
	 * @return
	 */
	public List<String> findChildCategoryByParentCategory(List<Integer> parentIdList);

}
