package com.hardwaremartapi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	private String orderId;
	private String userId;
	private String name;
	private String date;
	private String deliveryAddress;
	private int totalAmount;
	private String contactNumber;
	private String deliveryOption;
	private String shippingStatus;
	private String paymentOption;
	ArrayList<OrderItems> itemList;
	private long timestamp;

	public Order() {

	}

	public Order(String orderId, String userId, String name, String date, String deliveryAddress, int totalAmount,
			String contactNumber, String deliveryOption, String shippingStatus, ArrayList<OrderItems> itemList,
			String paymentOption, long timestamp) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.name = name;
		this.date = date;
		this.deliveryAddress = deliveryAddress;
		this.totalAmount = totalAmount;
		this.contactNumber = contactNumber;
		this.deliveryOption = deliveryOption;
		this.shippingStatus = shippingStatus;
		this.itemList = itemList;
		this.paymentOption = paymentOption;
		this.timestamp = timestamp;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		this.deliveryOption = deliveryOption;
	}

	public String getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public ArrayList<OrderItems> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<OrderItems> itemList) {
		this.itemList = itemList;
	}
}
