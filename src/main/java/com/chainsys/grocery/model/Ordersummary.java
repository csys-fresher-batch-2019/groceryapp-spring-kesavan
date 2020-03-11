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
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getNoofitems() {
		return noofitems;
	}

	public void setNoofitems(int noofitems) {
		this.noofitems = noofitems;
	}

	public int getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Date getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	private int orderid;
	private String productname;
	private String manufacturer;
	private int noofitems;
	private int totalamount;
	private Date orderdate;
	private Date deliverydate;
	private String deliveryaddress;
	private String orderstatus;
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
		return "Ordersummary [orderid=" + orderid + ", productname=" + productname + ", manufacturer=" + manufacturer
				+ ", noofitems=" + noofitems + ", totalamount=" + totalamount + ", orderdate=" + orderdate
				+ ", deliverydate=" + deliverydate + ", deliveryaddress=" + deliveryaddress + ", orderstatus="
				+ orderstatus + ", payment=" + payment + ", transId=" + transId + "]";
	}


}
