package com.philippe75.libraryWS.model.exception.saop;

import com.philippe75.libraryWS.model.exception.soap.fault.LibraryServiceFault;

public class LibraryServiceException extends Exception {
	
	private LibraryServiceFault fault;
	
	public LibraryServiceException(String message, LibraryServiceFault fault) {
		super(message);
		this.fault = fault;
	}
	
	public LibraryServiceException(String message, Throwable cause, LibraryServiceFault fault) {
		super(message);
		this.fault = fault;
	}

	public LibraryServiceFault getFault() {
		return fault;
	}
	
}
