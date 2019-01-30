package com.philippe75.libraryWS.model.exception;

import com.philippe75.libraryWS.model.exception.fault.AuthentificationFault;

public class AuthentificationException extends Exception {
	
	private AuthentificationFault fault;
	
	public AuthentificationException(String message, AuthentificationFault fault) {
		super(message);
		this.fault = fault;
	}
	
	public AuthentificationException(String message, Throwable cause, AuthentificationFault fault) {
		super(message);
		this.fault = fault;
	}

	public AuthentificationFault getFault() {
		return fault;
	}
	
}
