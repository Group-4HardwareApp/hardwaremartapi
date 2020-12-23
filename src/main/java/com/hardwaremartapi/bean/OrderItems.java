package com.hardwaremartapi.bean;

import java.io.Serializable;

public class OrderItems implements Serializable {
    private String productId;
    private int qty;
    private String name;
    private double amount;
    private String imageUrl;
    private double price;
    private String shopkeeperId;
    private String orderItemId;
    
    public OrderItems() {    	
    }

    public OrderItems(String productId, int qty, String productName, double amount,String imageUrl, double price,
			String shopkeeperId,String orderItemId) {
		super();
		this.productId = productId;
		this.qty = qty;
		this.name = productName;
		this.amount = amount;
		this.imageUrl = imageUrl;
		this.price = price;
		this.shopkeeperId = shopkeeperId;
		this.orderItemId = orderItemId;
	}
    

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getProductId() {
		return productId;
	}
    
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setqty(int qty) {
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String productName) {
		this.name = productName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getShopkeeperId() {
		return shopkeeperId;
	}

	public void setShopkeeperId(String shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
	}
    
    
}
