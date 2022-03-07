package com.tpp.ica.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.sevice.AccountOpeningSevice;
/**
 * Account opening event controller
 * 
 * @author adu11
 *
 */
@RestController
@RequestMapping(path = "/ica/api")
public class AccountOpeningController {
	
	private Logger logger = LoggerFactory.getLogger(AccountOpeningController.class);
	
	@Autowired
	private AccountOpeningSevice accountOpeningService;
	
	
	@PostMapping(path = "/account", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String processAccountOpening(@RequestBody String account) throws JsonProcessingException, InValidJsonInputMessage {
		logger.info("Open account rest endpoint");
		accountOpeningService.processAccountOpening(account);
		return account;
	}

}
