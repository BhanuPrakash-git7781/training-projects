package com.neosoft.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.test.bean.Limits;
import com.neosoft.test.configuration.LimitsConfiguration;

@RestController
public class LimitsController {
	
	@Autowired
	private LimitsConfiguration config;

	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(config.getMinimum(),config.getMaximum());
	}
}
