package com.mastercard.decisionmanager;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ExternalService {
	private final WebClient externalAPI = WebClient.create("http://www.randomnumberapi.com/api/v1.0/");
	
	public int getTimesUsed() {
		// External service call to get the number of times this card was used in the last seven days, reduced to a sum
		return externalAPI
						.get()
						.uri("random?min=0&max=12&count=7")
						.accept(MediaType.APPLICATION_JSON)
						.retrieve()
						.bodyToFlux(Integer.class)
						.onErrorResume(error -> Flux.fromStream(new Random().ints(7, 0, 12).boxed())) // in case the external service doesn't work
						.reduce(0, Integer::sum)
						.block();
	}
	
	// FOR TESTING: returns the sum of a passed in array of ints -- used to remove randomness
	public int getTimesUsed(int[] list) {
		return IntStream.of(list).sum();
	}
}