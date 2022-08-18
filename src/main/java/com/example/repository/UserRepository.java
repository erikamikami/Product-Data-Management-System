package com.example.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.User;

@Mapper
public interface UserRepository {
	
	/**
	 * nameからuserを特定
	 * @return
	 */
	public User findByName(String name);
	
	/**
	 * userを新規登録
	 * @param user
	 */
	public void insert(User user);

}
