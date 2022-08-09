package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
	
}
