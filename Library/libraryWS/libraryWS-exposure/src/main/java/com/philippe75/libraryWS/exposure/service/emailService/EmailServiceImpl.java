package com.philippe75.libraryWS.exposure.service.emailService;

import javax.jws.WebService;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
@WebService(endpointInterface="com.philippe75.libraryWS.exposure.service.emailService.EmailService")
public class EmailServiceImpl extends SpringBeanAutowiringSupport implements EmailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessage.setContent(text, "text/html");
			mmh.setTo(to);
			mmh.setSubject(subject);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
