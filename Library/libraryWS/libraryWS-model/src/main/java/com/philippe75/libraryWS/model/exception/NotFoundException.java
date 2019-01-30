package com.philippe75.libraryWS.model.exception;

public class NotFoundException extends Exception{

	/**
	 * Default constructor 
	 */
	public NotFoundException() {}
	
	/**
	 * Constructor 
	 * 
	 * @param message
	 */
	public NotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Constructor 
	 * 
	 * @param message
	 * @param reason
	 */
	public NotFoundException(String message, Throwable reason) {
		super(message, reason);
	}
}
