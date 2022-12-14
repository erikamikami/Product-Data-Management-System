package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.Item;
import com.example.entity.ItemSearch;
import com.example.pagination.Pagination;

@Mapper
public interface ItemRepository {
	
	/**
	 * itemを全件検索する
	 * @param pagination
	 * @return
	 */
	public List<Item> findAll(Pagination pagination);
	
	/**
	 * idからitemを1件取得する
	 * @param id
	 * @return
	 */
	public Item findById(int id);

	/**
	 * idの最大値を取得する
	 * @return
	 */
	public int findMaxId();
	
	/**
	 * itemを1件登録する
	 * @param item
	 */
	public void insert(Item item);
	
	/**
	 * itemを更新する
	 * @param item
	 */
	public void update(Item item);
	
	
	/**
	 * itemを検索する
	 * 
	 * 検索項目
	 * ・name
	 * ・parentCayegory
	 * ・childCategory
	 * ・grandChild
	 * ・brandName
	 * 
	 * @param item
	 * @return
	 */
	public List<Item> search(@Param("itemSearch") ItemSearch itemSearch, @Param("pagination") Pagination pagination);

	/**
	 * itemテーブル 総レコード数を取得
	 * @return
	 */
	public int countItems(ItemSearch itemSearch);

}
