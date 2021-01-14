package com.hardwaremartapi.bean;

public class Cart {
	private String cartId;
	private String userId;
	private String categoryId;
	private String productId;
	private String name;
	private double price;
	private String brand;
	private String imageUrl;
	private String description;
	private String shopkeeperId;
    private double qtyInStock;
    private int qty;
    private double total;
    
    
	public Cart(String cartId, String userId, String categoryId, String productId, String name, double price,
			String brand, String imageUrl, String description, String shopkeeperId, int qty, double total) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.categoryId = categoryId;
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.imageUrl = imageUrl;
		this.description = description;
		this.shopkeeperId = shopkeeperId;
		this.qty = qty;
		this.total = total;
	}

	public Cart() {
	}
	
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getQtyInStock() {
		return qtyInStock;
	}

	public void setQtyInStock(double qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShopkeeperId() {
		return shopkeeperId;
	}

	public void setShopkeeperId(String shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
	}

}
