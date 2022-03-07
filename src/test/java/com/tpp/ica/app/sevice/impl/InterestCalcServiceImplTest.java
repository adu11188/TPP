package com.tpp.ica.app.sevice.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpp.ica.app.entity.InterestDetails;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.mapper.InterestDetailsMapper;
import com.tpp.ica.app.repository.InterestDetailsRepository;
import com.tpp.ica.app.sevice.vo.InterestCalcVO;
import com.tpp.ica.app.sevice.vo.MonthlyInterestVO;


@ExtendWith(MockitoExtension.class)
class InterestCalcServiceImplTest {

	@Mock
	private InterestDetailsMapper interestDetailsMapper;
	
	@Mock
	private InterestDetailsRepository interestDetailsRepository;
	
	@Mock
	private BigDecimal annualInterestRate;
	
	@InjectMocks
	InterestCalcServiceImpl interestCalcServiceImpl ;
	
	@Test
	void test_processAccountEndOfDayBalances_null_message() throws JsonProcessingException, InValidJsonInputMessage{
		assertThatThrownBy(()-> {interestCalcServiceImpl.processAccountEndOfDayBalances(null);}).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	void test_processAccountEndOfDayBalances_invalidjson_message() throws JsonProcessingException, InValidJsonInputMessage {
		String message =  "{}";
		assertThatThrownBy(()-> {interestCalcServiceImpl.processAccountEndOfDayBalances(message);}).isInstanceOf(InValidJsonInputMessage.class);
	}

	
	@Test
	void test_processAccountEndOfDayBalances_validjson_message() throws JsonProcessingException, InValidJsonInputMessage {
		String message = "{\"balanceDate\":\"2022-01-04\",\"identification\":1,\"balance\":10}";
		Mockito.doNothing().when(interestDetailsMapper).interestDetailsVoToEntityMapper(Mockito.any(InterestCalcVO.class), Mockito.any(InterestDetails.class));
		Mockito.when(annualInterestRate.divide(Mockito.any(BigDecimal.class),Mockito.anyInt(),Mockito.any( RoundingMode.class))).thenReturn(new BigDecimal(2));
		InterestDetails id = interestCalcServiceImpl.processAccountEndOfDayBalances(message);
		assertEquals(id.getInterestAmount(), new BigDecimal(20));
	}
	
	@Test
	void test_processAccountEndOfDayBalances_jsonprocessingException() throws JsonProcessingException, InValidJsonInputMessage {
		String message = "{\"bsb\":1,\"identification\":1aa,\"openingDate\":\"2022-01-01\"}";
		assertThatThrownBy(()-> {interestCalcServiceImpl.processAccountEndOfDayBalances(message);}).isInstanceOf(JsonProcessingException.class);
	}
	@Test
	void test_calculateMonthlyInterest() {
		Mockito.when(interestDetailsRepository.getMonthlInterest(1, 1, 2022)).thenReturn("100.01");
		MonthlyInterestVO monthlyInterest = interestCalcServiceImpl.calculateMonthlyInterest(1, 1, 2022);
		BigDecimal expected = new BigDecimal(100.01);
		assertEquals(0,monthlyInterest.getMontlyInterestAmount().compareTo(expected.setScale(2, RoundingMode.HALF_DOWN)));
	}
	
	@Test
	void test_calculateMonthlyInterest_returns_null() {
		Mockito.when(interestDetailsRepository.getMonthlInterest(1, 1, 2022)).thenReturn(null);
		MonthlyInterestVO monthlyInterest = interestCalcServiceImpl.calculateMonthlyInterest(1, 1, 2022);
		assertEquals(null,monthlyInterest.getMontlyInterestAmount());
	}
}
