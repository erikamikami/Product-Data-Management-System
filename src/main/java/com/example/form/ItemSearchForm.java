package com.example.form;

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
public class ItemSearchForm {
	private String name;
	private String parentCategory;
	private String childCategory;
	private String grandChild;
	private String brandName;

}
