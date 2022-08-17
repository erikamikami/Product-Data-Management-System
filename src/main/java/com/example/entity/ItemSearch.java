package com.example.entity;

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemSearch {
	private String name;
	private String parentCategory;
	private String childCategory;
	private String grandChild;
	private String brandName;
	
	/**
	 * フィールドの値が全てnullかどうか判定する
	 * 
	 * @return
	 * 全てnullまたは空文字の場合：true
	 * 全てnullまたは空文字ではない場合：false
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public boolean isAllAtributesNull() throws IllegalArgumentException, IllegalAccessException  {
		int fieldQuantity = 5;
		int nullCount = 0;
		
		for(Field field : this.getClass().getDeclaredFields()) {
			if(field.get(this)==null || field.get(this)=="") {
				nullCount++;
			}
		}
		
		if(nullCount==fieldQuantity) {
			return true;
		}else {
			return false;
		}
	}

}
