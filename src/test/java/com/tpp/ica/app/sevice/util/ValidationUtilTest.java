package com.tpp.ica.app.sevice.util;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpp.ica.app.exception.InValidJsonInputMessage;

@ExtendWith(MockitoExtension.class)
class ValidationUtilTest {
	
	
	@Test
	void test_validateAccountCreationInput() throws JsonProcessingException, InValidJsonInputMessage {
		String message = "{\"bsb\":1,\"identification\":1,\"openingDate\":\"2022-01-01\"}";
		ValidationUtil.validateAccountCreationInput(message);
	}
	@Test
	void test_validateAccountCreationInput_invalidinput_throws_JsonProcessingException() {
		String message = "{\"bsb\":1,\"identification\":1aa,\"openingDate\":\"2022-01-01\"}";
		assertThatThrownBy(()-> {ValidationUtil.validateAccountCreationInput(message);}).isInstanceOf(JsonProcessingException.class);
	}
	
	@Test
	void test_validateAccountCreationInput_invalidinput_throws_illegalargument_for_null_value() {
		assertThatThrownBy(()-> {ValidationUtil.validateAccountCreationInput(null);}).isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	void test_validateAccountCreationInput_invalidinput_throws_InValidJsonInputMessage() {
		String message = "{\"bsb\":1,\"identification\":1}";
		assertThatThrownBy(()-> {ValidationUtil.validateAccountCreationInput(message);}).isInstanceOf(InValidJsonInputMessage.class);
	}
	
	@Test
	void test_validateInterestCalcInput() throws JsonProcessingException, InValidJsonInputMessage {
		String message ="{\"balanceDate\":\"2022-01-04\",\"identification\":1,\"balance\":10}";
		ValidationUtil.validateInterestCalcInput(message);
	}
	
	@Test
	void test_validateInterestCalcInput_invalidinput_throws_JsonProcessingException() {
		String message ="{\"balanceDate\":axax\"2022-01-04\",\"identification\":1,\"balance\":10}";
		assertThatThrownBy(()-> {ValidationUtil.validateInterestCalcInput(message);}).isInstanceOf(JsonProcessingException.class);
	}
	
	@Test
	void test_validateInterestCalcInput_invalidinput_throws_InValidJsonInputMessage() {
		String message = "{\"balanceDate\":\"2022-01-04\",\"identification\":1}";
		assertThatThrownBy(()-> {ValidationUtil.validateInterestCalcInput(message);}).isInstanceOf(InValidJsonInputMessage.class);
	}

	@Test
	void test_validateInterestCalcInput_invalidinput_throws_illegalargument_for_null_value() {
		assertThatThrownBy(()-> {ValidationUtil.validateInterestCalcInput(null);}).isInstanceOf(IllegalArgumentException.class);
	}
}
