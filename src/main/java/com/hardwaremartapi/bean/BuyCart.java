package com.hardwaremartapi.bean;

import java.util.ArrayList;

public class BuyCart {
	private ArrayList<Cart> cartList;

	public BuyCart() {

	}

	public BuyCart(ArrayList<Cart> cartList) {
		super();
		this.cartList = cartList;
	}

	public ArrayList<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(ArrayList<Cart> cartList) {
		this.cartList = cartList;
	}
}