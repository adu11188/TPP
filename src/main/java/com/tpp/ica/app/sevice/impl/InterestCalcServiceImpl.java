package com.tpp.ica.app.sevice.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.tpp.ica.app.entity.InterestDetails;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.mapper.InterestDetailsMapper;
import com.tpp.ica.app.repository.InterestDetailsRepository;
import com.tpp.ica.app.sevice.InterestCalcService;
import com.tpp.ica.app.sevice.util.ValidationUtil;
import com.tpp.ica.app.sevice.vo.InterestCalcVO;
import com.tpp.ica.app.sevice.vo.MonthlyInterestVO;

/**
 * Interest calculation service
 * 
 * @author adu11
 *
 */
@Service
public class InterestCalcServiceImpl implements InterestCalcService{
	
	private static final Logger logger = LoggerFactory.getLogger(InterestCalcServiceImpl.class);
	
	private ObjectMapper mapper = JsonMapper.builder().addModule(new ParameterNamesModule())
			   .addModule(new Jdk8Module())
			   .addModule(new JavaTimeModule())
			   .build();
	
	@Autowired
	private InterestDetailsMapper interestDetailsMapper;
	
	@Autowired
	private InterestDetailsRepository interestDetailsRepository;
	
	@Value("${annual.interest.rate}")
	private BigDecimal annualInterestRate;
	
	
	public InterestDetails processAccountEndOfDayBalances(String message) throws JsonProcessingException, InValidJsonInputMessage {
 		logger.debug("Validating message received");
		ValidationUtil.validateInterestCalcInput(message);
		InterestCalcVO interestCalcVO = mapper.readValue(message, InterestCalcVO.class);
		InterestDetails interestDetails = new InterestDetails();
		interestDetailsMapper.interestDetailsVoToEntityMapper(interestCalcVO, interestDetails);
		BigDecimal interestAmount =  calculateDailyInterestAmount(interestCalcVO.getBalance(), interestCalcVO.getBalanceDate());
		interestDetails.setInterestAmount(interestAmount);
		logger.debug("Saving interest details");
		interestDetailsRepository.save(interestDetails);
		return interestDetails;
	}
	
	private BigDecimal calculateDailyInterestAmount(BigDecimal balance, LocalDate date) {
		BigDecimal interestAmount = null;
		BigDecimal lengthOfTheYear = new BigDecimal(date.lengthOfYear());
		BigDecimal dailyRate = annualInterestRate.divide(lengthOfTheYear,2, RoundingMode.HALF_DOWN);
		interestAmount = balance.multiply(dailyRate);
		return interestAmount;
	}
	
	public MonthlyInterestVO calculateMonthlyInterest(int id, int month,int year) {
		MonthlyInterestVO result = new MonthlyInterestVO();
		String monthlyinterest = interestDetailsRepository.getMonthlInterest(id, month, year);
		result.setMontlyInterestAmount(monthlyinterest!=null?new BigDecimal(monthlyinterest).setScale(2,RoundingMode.HALF_DOWN):null);
		return result;
	}

}
