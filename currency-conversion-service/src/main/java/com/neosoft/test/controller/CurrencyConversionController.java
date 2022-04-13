package com.neosoft.test.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.neosoft.test.bean.CurrencyConversion;
import com.neosoft.test.feign.CurrencyExchangeProxy;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	//@Retry(name ="api-test")
	@Retry(name ="api-test",fallbackMethod = "hardcodedResponse")	
	public ResponseEntity<CurrencyConversion> getConversionValue(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity){
		
		log.info("Calling currency-conversion -> currency-exchange {}");
		
//		HashMap<String, String> uriVariables = new HashMap<String, String>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//		
//		ResponseEntity<CurrencyConversion> responseEntity = 
//				new RestTemplate().getForEntity
//				("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
//				CurrencyConversion.class,uriVariables);
//				
//		CurrencyConversion currencyConversion = responseEntity.getBody();
		
		CurrencyConversion currencyConversion = proxy.getExchangeValue(from, to);
		
		return ResponseEntity.ok(new CurrencyConversion(
				currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" rest template"));							
	}	
	
	public ResponseEntity<Object> hardcodedResponse(Exception e) {
		return ResponseEntity.internalServerError().body("Service is down..");
	}
}
