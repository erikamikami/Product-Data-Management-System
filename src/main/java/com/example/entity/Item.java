package com.example.entity;

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
	
	private int id;
	private String name;
	private int condition;
	private String category;
	private String brandName;
	private double price;
	private int shipping;
	private String itemDescription;

}
