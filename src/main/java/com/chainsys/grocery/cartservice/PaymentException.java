package com.chainsys.grocery.cartservice;

public class PaymentException extends Exception {

	public PaymentException(Exception e, String message) {
		super(message,e);
	}

	
}
