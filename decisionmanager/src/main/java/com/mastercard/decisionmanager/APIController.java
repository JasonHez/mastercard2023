package com.mastercard.decisionmanager;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("api/v1")
public class APIController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private AnalysisService service;
	
	@PostMapping("/analyzeTransaction")
	public TransactionAnalysis analyzeTransaction(@RequestBody Transaction transaction) {
		TransactionAnalysis transactionAnalysis = service.analyzeTransaction(transaction);
		LOGGER.info("Analyzed transaction: " + transactionAnalysis);
		return transactionAnalysis;
	}
}
