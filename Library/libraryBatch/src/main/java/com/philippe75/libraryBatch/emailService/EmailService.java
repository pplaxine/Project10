package com.philippe75.libraryBatch.emailService;

import org.springframework.stereotype.Component;

public interface EmailService {
	
	void sendSimpleMessage(String to, String subject, String text);
}
