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


@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getConversionValue(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity){
		
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
		
		return new CurrencyConversion(
				currencyConversion.getId(), 
				from, to, quantity, 
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment()+" rest template");		
	}
}