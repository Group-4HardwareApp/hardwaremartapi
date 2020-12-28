package com.hardwaremartapi.bean;

public class Comment {

	private String commentId;
	private String date;
	private long timestamp;
	private String userId;
	private String orderId;
	private String comment;
	private String productId;
	private String rating;
	private String userName;
	private String userImg;

	public Comment() {

	}

	public Comment(String date, long timestamp, String userId, String orderId, String comment, String productId,
			String rating, String userName, String userImg) {
		super();
		this.date = date;
		this.timestamp = timestamp;
		this.userId = userId;
		this.orderId = orderId;
		this.comment = comment;
		this.productId = productId;
		this.rating = rating;
		this.userName = userName;
		this.userImg = userImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
