package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemForm {
	
	private String id;
	
	@NotBlank(message = "error:may not be empty")
	private String name;

	@NotNull(message = "error:may not be empty")
	private Integer condition;
	
	private String category;

	@NotBlank(message = "error:may not be empty")
	private String parentCategory;

	@NotBlank(message = "error:may not be empty")
	private String childCategory;

	private String grandChild;

	private String brandName;
	
	@NotBlank(message = "error:may not be empty")
	private String price;
	
	@NotNull(message = "error:may not be empty")
	private Integer shipping;
	
	@NotBlank(message = "error:may not be empty")
	private String itemDescription;
	
  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getShipping() {
		return shipping;
	}
	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
}
