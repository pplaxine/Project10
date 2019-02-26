package com.philippe75.libraryWS.exposure.service.emailService;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EmailService {
	
	void sendSimpleMessage(String to, String subject, String text);
}
