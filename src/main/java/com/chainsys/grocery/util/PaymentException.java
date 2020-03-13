package com.chainsys.grocery.util;

public class PaymentException extends Exception {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentException(Exception e, String message) {
		super(message,e);
	}

	
}
