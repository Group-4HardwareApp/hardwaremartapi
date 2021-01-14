package com.hardwaremartapi.bean;

public class Shopkeeper {
	private String name;
	private String shopkeeperId;
	private String shopName;
	private String contactNumber;
	private String address;
	private String imageUrl;
	private String email;
	private String token;

	public Shopkeeper(String shopkeeperId, String shopName, String contactNumber, String address, String imageUrl,
			String email, String token, String name) {
		super();
		this.shopkeeperId = shopkeeperId;
		this.shopName = shopName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.imageUrl = imageUrl;
		this.email = email;
		this.token = token;
		this.name = name;
	}

	public Shopkeeper() {
	}

	public String getShopkeeperId() {
		return shopkeeperId;
	}

	public void setShopkeeperId(String shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
