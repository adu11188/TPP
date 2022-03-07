package com.tpp.ica.app.sevice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tpp.ica.app.exception.InValidJsonInputMessage;

public interface AccountOpeningSevice {
	
	void processAccountOpening(String message) throws JsonProcessingException, InValidJsonInputMessage;
	
}
