package com.example;

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
public class Item {
	
	private String id;
	private String name;
	private String condition;
	private String category;
	private String brandName;
	private double price;
	private String shipping;
	private String itemDescription;

}
