package com.chainsys.grocery.model;

public class UserProfile {

	// ORDER DETAILS
	private int productId;
	private int noOfItems;

	public int getProductid() {
		return productId;
	}

	public void setProductid(int productid) {
		this.productId = productid;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String toString() {
		return "UserProfile [productid=" + productId + ", noOfItems=" + noOfItems + "]";
	}

}
