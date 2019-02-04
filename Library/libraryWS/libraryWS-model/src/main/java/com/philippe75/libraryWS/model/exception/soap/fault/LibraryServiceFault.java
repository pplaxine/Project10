package com.philippe75.libraryWS.model.exception.soap.fault;

public class LibraryServiceFault {
	
	private String faultCode;
	
	private String faultMessage;

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultMessage() {
		return faultMessage;
	}

	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}
	
	
	
}
