package com.tpp.ica.app.sevice.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpp.ica.app.entity.AccountDetails;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.mapper.AccountDetailsMapper;
import com.tpp.ica.app.repository.AccountDetailsRepository;
import com.tpp.ica.app.sevice.AccountOpeningSevice;
import com.tpp.ica.app.sevice.util.ValidationUtil;
import com.tpp.ica.app.sevice.vo.AccountVO;

/**
 * Create account Service implementation
 * 
 * @author adu11
 *
 */
@Service
public class AccountOpeningServiceImpl implements AccountOpeningSevice {

	private Logger logger = LoggerFactory.getLogger(AccountOpeningServiceImpl.class);
	
	@Autowired
	private AccountDetailsRepository accountDetailsRepository;
	
	@Autowired
	private AccountDetailsMapper accountDetailsMapper;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/*
	 * Create account input - idempotent processing of creation of account 
	 * Validate received message
	 * Map to entity
	 * Save to database
	 */
	@Override
	public void processAccountOpening(String message) throws JsonProcessingException, InValidJsonInputMessage {
		logger.debug("Validating message received");
		ValidationUtil.validateAccountCreationInput(message);
		//convert string to value object
		AccountVO accountVo = mapper.readValue(message, AccountVO.class);
		
		//account details entity
		AccountDetails accountDetails = new AccountDetails();
		
		//map VO to entity
		accountDetailsMapper.accountDetailsVoToEntityMapper(accountVo, accountDetails);
		logger.debug("Saving account details");
		
		//Save entity to Database
		accountDetailsRepository.save(accountDetails);
	}
	
	

}
