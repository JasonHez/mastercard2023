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
	private AnalysisService analysisService;
	
	@Autowired
	private ExternalService externalAPI;
	
	@PostMapping("/analyzeTransaction")
	public TransactionAnalysis analyzeTransaction(@RequestBody Transaction transaction) {
		int timesUsed = externalAPI.getTimesUsed(); // 'External' service call to get the number of times this card was used in the last seven days
		TransactionAnalysis transactionAnalysis = analysisService.analyzeTransaction(transaction, timesUsed);
		LOGGER.info("Analyzed transaction: " + transactionAnalysis);
		return transactionAnalysis;
	}
}
