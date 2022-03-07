package com.tpp.ica.app.sevice.util;

import java.io.InputStream;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.tpp.ica.app.exception.InValidJsonInputMessage;
/**
 * Utility functions for validation of input json using json schema
 * 
 * @author adu11
 *
 */
public class ValidationUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtil.class);
	private static final JsonSchema AccountOpeningEventMessageValidationSchema = getJsonSchemaFromClasspath("AccountOpeningEventMessageValidationSchema.json");
	private static final JsonSchema InterestCalcEventMessageValidationSchema = getJsonSchemaFromClasspath("InterestCalcEventMessageValidationSchema.json");
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private ValidationUtil() {}
	
    private static JsonSchema getJsonSchemaFromClasspath(String name) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(name);
        return factory.getSchema(is);
    }
    
    public static void validateAccountCreationInput(String accountMessage) throws JsonProcessingException, InValidJsonInputMessage {
    	JsonNode jsonNode = mapper.readTree(accountMessage);
    	Set<ValidationMessage> validationErrors = AccountOpeningEventMessageValidationSchema.validate(jsonNode);
    	checkAndThrowException(validationErrors);
    	
    }

    
    public static void validateInterestCalcInput(String interestCalcMessage) throws JsonProcessingException, InValidJsonInputMessage {
    	JsonNode jsonNode = mapper.readTree(interestCalcMessage);
    	Set<ValidationMessage> validationErrors = InterestCalcEventMessageValidationSchema.validate(jsonNode);
    	checkAndThrowException(validationErrors);
    	
    }
    
    
	private static void checkAndThrowException(Set<ValidationMessage> validationErrors) throws InValidJsonInputMessage {
		if(!validationErrors.isEmpty()) {
        	LOGGER.error("Json input received from message have validation errors");
    		throw new InValidJsonInputMessage(validationErrors.toString());
    	}
	}
    
}
