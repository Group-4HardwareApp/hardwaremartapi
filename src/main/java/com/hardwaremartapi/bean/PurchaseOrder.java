package com.hardwaremartapi.bean;

import java.util.ArrayList;

public class PurchaseOrder {

	private String orderId;
	private String shippingStatus;
	private String date;
	private double totalAmount;
	private ArrayList<OrderItems> itemList;

	public PurchaseOrder() {

	}

	public PurchaseOrder(String orderId, String shippingStatus, String date, double totalAmount,
			ArrayList<OrderItems> itemList) {
		super();
		this.orderId = orderId;
		this.shippingStatus = shippingStatus;
		this.date = date;
		this.totalAmount = totalAmount;
		this.itemList = itemList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ArrayList<OrderItems> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<OrderItems> itemList) {
		this.itemList = itemList;
	}
}
