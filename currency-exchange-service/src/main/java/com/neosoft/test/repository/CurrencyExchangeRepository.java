package com.neosoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.test.bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

	CurrencyExchange findByFromAndTo(String from, String to);
}
