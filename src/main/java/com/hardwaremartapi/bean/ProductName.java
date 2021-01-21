package com.hardwaremartapi.bean;

public class ProductName {
	private String productNameId;
	private String categoryId;
	private String productName;

	public ProductName() {
	}

	public ProductName(String productNameId, String categoryId, String productName) {
		super();
		this.productNameId = productNameId;
		this.categoryId = categoryId;
		this.productName = productName;
	}

	public String getProductNameId() {
		return productNameId;
	}

	public void setProductNameId(String productNameId) {
		this.productNameId = productNameId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
