package com.chainsys.grocery.model;

import java.sql.Date;

public class OrderSummary {

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getProductname() {
		return productName;
	}

	public void setProductname(String productname) {
		this.productName = productname;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getNoofitems() {
		return noOfItems;
	}

	public void setNoofitems(int noofitems) {
		this.noOfItems = noofitems;
	}

	public int getTotalamount() {
		return totalAmount;
	}

	public void setTotalamount(int totalamount) {
		this.totalAmount = totalamount;
	}

	public Date getOrderdate() {
		return orderDate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderDate = orderdate;
	}

	public Date getDeliverydate() {
		return deliveryDate;
	}

	public void setDeliverydate(Date deliverydate) {
		this.deliveryDate = deliverydate;
	}

	public String getDeliveryaddress() {
		return deliveryAddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryAddress = deliveryaddress;
	}

	public String getOrderstatus() {
		return orderStatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderStatus = orderstatus;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	private int orderid;
	private String productName;
	private String manufacturer;
	private int noOfItems;
	private int totalAmount;
	private Date orderDate;
	private Date deliveryDate;
	private String deliveryAddress;
	private String orderStatus;
	private String payment;
	private int transId;

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	@Override
	public String toString() {
		return "Ordersummary [orderid=" + orderid + ", productname=" + productName + ", manufacturer=" + manufacturer
				+ ", noofitems=" + noOfItems + ", totalamount=" + totalAmount + ", orderdate=" + orderDate
				+ ", deliverydate=" + deliveryDate + ", deliveryaddress=" + deliveryAddress + ", orderstatus="
				+ orderStatus + ", payment=" + payment + ", transId=" + transId + "]";
	}

}
