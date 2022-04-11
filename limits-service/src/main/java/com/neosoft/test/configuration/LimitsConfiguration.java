package com.neosoft.test.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("limits-service")
public class LimitsConfiguration {

	private int minimum;
	private int maximum;
}
