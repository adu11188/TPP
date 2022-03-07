package com.tpp.ica.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpp.ica.app.entity.InterestDetails;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.sevice.InterestCalcService;
import com.tpp.ica.app.sevice.vo.MonthlyInterestVO;

@RestController
@RequestMapping(path = "/ica/api")
public class InterestController {

	
private Logger logger = LoggerFactory.getLogger(InterestController.class);
	
	@Autowired
	private InterestCalcService interestCalcService;
	
	
	@PostMapping(path = "/account/daily/interest", consumes = MediaType.APPLICATION_JSON_VALUE)
	public InterestDetails processAccountEndOfDayBalances(@RequestBody String message) throws JsonProcessingException, InValidJsonInputMessage {
		logger.info("Calculate interest for a date rest endpoint");
		return interestCalcService.processAccountEndOfDayBalances(message);
	}

	
	@GetMapping(path = "/account/monthly/interest/id/{id}/month/{month}/year/{year}")
	public MonthlyInterestVO calculateMonthlyInterest(@PathVariable("id") int id,@PathVariable("month") int month,@PathVariable("year") int year) {
		logger.info("Calculate interest for a month rest endpoint");
		return interestCalcService.calculateMonthlyInterest(id,month, year);
	}
}
