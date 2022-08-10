package com.example.form;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemForm {
	
	private int id;
	private String name;
	private int condition;
	private String category;
	private String parentCategory;
	private String childCategory;
	private String grandChild;
	private String brandName;
	private double price;
	private int shipping;
	private String itemDescription;
	
	
	public int getId() {
		return id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = Integer.parseInt(condition);
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String parentCategory, String childCategory, String grandChild) {
		this.category = parentCategory + "/" + childCategory + "/" + grandChild;
	}
	public String getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getChildCategory() {
		return childCategory;
	}
	public void setChildCategory(String childCategory) {
		this.childCategory = childCategory;
	}
	public String getGrandChild() {
		return grandChild;
	}
	public void setGrandChild(String grandChild) {
		this.grandChild = grandChild;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = Integer.parseInt(shipping);
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
}
