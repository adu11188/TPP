package com.tpp.ica.app.sevice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpp.ica.app.entity.InterestDetails;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.sevice.vo.MonthlyInterestVO;

public interface InterestCalcService {

	InterestDetails processAccountEndOfDayBalances(String message) throws JsonProcessingException, InValidJsonInputMessage;
	MonthlyInterestVO calculateMonthlyInterest(int id,int month,int year) ;

}
