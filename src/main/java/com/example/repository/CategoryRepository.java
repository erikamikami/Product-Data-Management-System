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
	 * parentIdListからchildCategoryを取得
	 * @return
	 */
	public List<String> findChildCategoryByParentCategoryId(List<Integer> parentIdList);
	
	/**
	 * childCategoryの名前からchildIdListを取得
	 * @return
	 */
	public List<Integer> findChildIdByChildCategory(String childCategory);
	
	
	/**
	 * childIdからgrandCategoryを取得
	 * @param chileIdList
	 * @return
	 */
	public List<String> findGrandChildCategoryByChildCategoryId(List<Integer> chileIdList);
	
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
