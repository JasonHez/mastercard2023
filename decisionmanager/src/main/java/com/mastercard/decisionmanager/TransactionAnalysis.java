package com.mastercard.decisionmanager;

public class TransactionAnalysis {
	private long cardNumber;
	private float transactionAmount;
	private boolean approved;
	private int timesUsed;
	
	public TransactionAnalysis(long _cardNumber, float _transactionAmount, boolean _approved, int _timesUsed) {
		this.cardNumber = _cardNumber;
		this.transactionAmount = _transactionAmount;
		this.approved = _approved;
		this.timesUsed = _timesUsed;
	}
	
	public void setCardNumber(long _cardNumber) {
		this.cardNumber = _cardNumber;
	}
	
	public void setTransactionAmount(float _transactionAmount) {
		this.transactionAmount = _transactionAmount;
	}
	
	public void setApproved(boolean _approved) {
		this.approved = _approved;
	}
	
	public void setTimesUsed(int _timesUsed) {
		this.timesUsed = _timesUsed;
	}
	
	public long getCardNumber() {
		return this.cardNumber;
	}
	
	public float getTransactionAmount() {
		return this.transactionAmount;
	}
	
	public boolean getApproved() {
		return this.approved;
	}
	
	public int getTimesUsed() {
		return this.timesUsed;
	}
	
	@Override
	public String toString() {
		String cardNumberString =  Long.toString(this.cardNumber);
		return cardNumberString.substring(0, 4) + "********" + cardNumberString.substring(12, 16) + " -- $"+ this.transactionAmount + " | " + this.timesUsed;
	}
}