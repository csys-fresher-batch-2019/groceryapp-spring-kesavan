package com.chainsys.grocery.dto;
@SuppressWarnings("unused")
public class WalletDTO {

	private long mobile;
	private int id;
	private int amount;

	public WalletDTO(long mobile, int id, int amount) {
		super();
		this.mobile = mobile;
		this.id = id;
		this.amount = amount;
	}
}
