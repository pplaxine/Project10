package com.philippe75.libraryWS.model.exception;

public class DataBaseException extends Exception{

	/**
	 * Default constructor 
	 */
	public DataBaseException() {}
	
	/**
	 * Constructor 
	 * 
	 * @param message
	 */
	public DataBaseException(String message) {
		super(message);
	}
	
	/**
	 * Constructor 
	 * 
	 * @param message
	 * @param reason
	 */
	public DataBaseException(String message, Throwable reason) {
		super(message, reason);
	}
}
