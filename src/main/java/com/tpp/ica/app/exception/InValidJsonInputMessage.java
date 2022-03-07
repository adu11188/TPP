package com.tpp.ica.app.exception;

/**
 * Schema validation failure exception checked
 * 
 * @author adu11
 *
 */
public class InValidJsonInputMessage extends ICABaseException {

	private static final long serialVersionUID = 1L;

	public InValidJsonInputMessage(String errorMessage) {
		super(errorMessage);
	}

	public InValidJsonInputMessage(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
