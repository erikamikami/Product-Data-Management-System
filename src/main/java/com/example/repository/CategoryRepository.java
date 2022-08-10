package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryRepository {
	
	public List<String> findParentCategory();

}
