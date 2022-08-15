package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Item;
import com.example.pagination.Pagination;

@Mapper
public interface ItemRepository {
	
	/**
	 * itemテーブルの総レコード数を取得
	 * @return
	 */
	public int countAllItems();
	
	/**
	 * itemを全て取得（ページングあり）
	 * @param pagination
	 * @return
	 */
	public List<Item> findAll(Pagination pagination);
	
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
	
}
