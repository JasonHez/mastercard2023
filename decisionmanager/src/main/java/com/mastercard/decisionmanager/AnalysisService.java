package com.mastercard.decisionmanager;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class AnalysisService {
	private final WebClient externalAPI = WebClient.create("http://www.randomnumberapi.com/api/v1.0/");
	
	public TransactionAnalysis analyzeTransaction(Transaction transaction) {
		// External service call to get the number of times this card was used in the last seven days, reduced to a sum
		int timesUsed = externalAPI
								.get()
								.uri("random?min=0&max=12&count=7")
								.accept(MediaType.APPLICATION_JSON)
								.retrieve()
								.bodyToFlux(Integer.class)
								.onErrorResume(error -> Flux.fromStream(new Random().ints(7, 0, 12).boxed())) // in case the external service doesn't work
								.reduce(0, Integer::sum)
								.block();
			
		// Fraud checks
		boolean approved = true;
		if (	transaction.getAmount() > 50000.00f
				|| timesUsed > 60 
				|| (timesUsed < 35 && transaction.getAmount()/timesUsed > 500))
			approved = false;
		
		return new TransactionAnalysis(transaction.getCardNum(), transaction.getAmount(), approved, timesUsed);
	}
}