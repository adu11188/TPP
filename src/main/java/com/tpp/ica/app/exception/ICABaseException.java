package com.tpp.ica.app.exception;
/**
 * Base checked exception for ICA application
 * 
 * @author adu11
 *
 */
public class ICABaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ICABaseException(String errorMessage) {
		super(errorMessage);
	}

	public ICABaseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
