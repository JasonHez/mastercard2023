package com.mastercard.decisionmanager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;



@JsonTypeName("transaction")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Transaction {

/*
	if (transaction.getAmount() < 0.f)
		throw new ValidationException("Transaction amount cannot be negative");
	if (transaction.getCardNum() < 1000000000000000L)
		throw new ValidationException("Card number cannot be less than 1000000000000000");
	if (transaction.getCardNum() > 9999999999999999L)
		throw new ValidationException("Card number cannot be greater than 9999999999999999");
	*/
	
	@JsonProperty(value="cardNum", required=true)
	private long cardNum;
	
	@JsonProperty(value="amount", required=true)
	private float amount;
	
	public Transaction(long _cardNum, float _amount) {
		this.cardNum = _cardNum;
		this.amount = _amount;
	}
	
	public void setCardNum(long _cardNum) {
		this.cardNum = _cardNum;
	}
	
	public void setAmount(float _amount) {
		this.amount = _amount;
	}
	
	public long getCardNum() {
		return this.cardNum;
	}
	
	public float getAmount() {
		return this.amount;
	}
}