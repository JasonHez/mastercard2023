package com.mastercard.decisionmanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DecisionmanagerApplicationTests {
	
	@Autowired
	private AnalysisService analyzer;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void invalidTransactions() {
		// Amount is greater than 50,000
		Transaction transaction = new Transaction(4000000000000000L, 50000.01f);
		Assertions.assertFalse(analyzer.analyzeTransaction(transaction, 1).getApproved());
		
		// Card used over 60 times 
		transaction = new Transaction(4000000000000000L, 1.00f);
		Assertions.assertFalse(analyzer.analyzeTransaction(transaction, 61).getApproved());
		
		// Transaction amount is $9000 and the card has been used 2 times in the last 7 days (9000/2 = 4500 > 500)
		transaction = new Transaction(4000000000000000L, 9000.0f);
		Assertions.assertFalse(analyzer.analyzeTransaction(transaction, 2).getApproved());
	}
	
	@Test
	public void validTransactions() {
		// Card used between 35 and 60 times in the last 7 days, amount is < 50,000
		Transaction transaction = new Transaction(4000000000000000L, 49999.99f);
		Assertions.assertTrue(analyzer.analyzeTransaction(transaction, 36).getApproved());
				
		// Card used once, amount is < 500 (499/1 < 500)
		transaction = new Transaction(4000000000000000L, 499.00f);
		Assertions.assertTrue(analyzer.analyzeTransaction(transaction, 1).getApproved());
	}
}
