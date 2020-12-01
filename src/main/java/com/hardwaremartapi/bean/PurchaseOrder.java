package com.hardwaremartapi.bean;

import java.util.ArrayList;

public class PurchaseOrder {
	private String orderId;
	private String orderDate;
	private String orderStatus;
	private double totalAmount;
	private ArrayList<OrderItems> itemList;
	
	public PurchaseOrder() {
	}
	
	public PurchaseOrder(String orderId, String orderStatus, String orderDate, double totalAmount, ArrayList<OrderItems> itemList) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.itemList = itemList;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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
