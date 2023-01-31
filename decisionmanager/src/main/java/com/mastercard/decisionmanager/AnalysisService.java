package com.mastercard.decisionmanager;

import org.springframework.stereotype.Service;

@Service
public class AnalysisService {
	
	public TransactionAnalysis analyzeTransaction(Transaction transaction, int timesUsed) {
		// Fraud checks
		boolean approved = true;
		if (	transaction.getAmount() > 50000.00f
				|| timesUsed > 60 
				|| (timesUsed < 35 && transaction.getAmount()/timesUsed > 500))
			approved = false;
		
		return new TransactionAnalysis(transaction.getCardNum(), transaction.getAmount(), approved, timesUsed);
	}
}