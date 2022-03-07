package com.tpp.ica.app.exception;

/**
 * Base unchecked exception for ICA application
 * 
 * @author adu11
 *
 */
public class ICABaseRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ICABaseRuntimeException(String errorMessage) {
		super(errorMessage);
	}
	public ICABaseRuntimeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
