package com.tpp.ica.app.sevice.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpp.ica.app.entity.AccountDetails;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
import com.tpp.ica.app.mapper.AccountDetailsMapper;
import com.tpp.ica.app.repository.AccountDetailsRepository;
import com.tpp.ica.app.sevice.vo.AccountVO;


@ExtendWith(MockitoExtension.class)
class AccountOpeningServiceImplTest {
	
	@Mock
	private AccountDetailsMapper accountDetailsMapper;
	
	@Mock
	private AccountDetailsRepository accountDetailsRepository;
	
	@InjectMocks
	AccountOpeningServiceImpl accountOpeningServiceImpl;
	@Test
	void test_processAccountOpening_null_message() throws JsonProcessingException, InValidJsonInputMessage{
		assertThatThrownBy(()-> {accountOpeningServiceImpl.processAccountOpening(null);}).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	void test_processAccountOpening_invalidjson_message() throws JsonProcessingException, InValidJsonInputMessage {
		String message =  "{}";
		assertThatThrownBy(()-> {accountOpeningServiceImpl.processAccountOpening(message);}).isInstanceOf(InValidJsonInputMessage.class);
	}

	
	@Test
	void test_processAccountOpening_validjson_message() throws JsonProcessingException, InValidJsonInputMessage {
		String message = "{\"bsb\":1,\"identification\":1,\"openingDate\":\"2022-01-01\"}";
		Mockito.doNothing().when(accountDetailsMapper).accountDetailsVoToEntityMapper(Mockito.any(AccountVO.class), Mockito.any(AccountDetails.class));
		accountOpeningServiceImpl.processAccountOpening(message);
	}
	
	@Test
	void test_processAccountOpening_jsonprocessingException() throws JsonProcessingException, InValidJsonInputMessage {
		String message = "{\"bsb\":1,\"identification\":1aa,\"openingDate\":\"2022-01-01\"}";
		assertThatThrownBy(()-> {accountOpeningServiceImpl.processAccountOpening(message);}).isInstanceOf(JsonProcessingException.class);
	}
}
