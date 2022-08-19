package com.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.User;

@Mapper
public interface UserRepository {
	
	/**
	 * nameからuserを特定
	 * @return
	 */
	public User findByName(String name);
	
	/**
	 * nameとpasswordからuserを特定
	 * @return
	 */
	public User findByNameAndPassword(@Param("name") String name, @Param("password") String password);
	
	/**
	 * userを新規登録
	 * @param user
	 */
	public void insert(User user);

}
