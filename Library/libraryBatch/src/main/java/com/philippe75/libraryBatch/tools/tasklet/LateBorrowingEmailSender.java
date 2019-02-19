package com.philippe75.libraryBatch.tools.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.philippe75.libraryBatch.emailService.EmailService;

@Component
public class LateBorrowingEmailSender implements Tasklet{

	@Autowired
	EmailService emailService;
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		//temporaire
		String emailHtml = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" + 
				"	<title>Library 7</title>\r\n" + 
				"	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body style=\"background-color: black;\">\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	  	<header>\r\n" + 
				"	  		<div align=\"center\" style=\"background-image: url('http://images.unsplash.com/photo-1533749047139-189de3cf06d3?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjEyMDd9');   \r\n" + 
				"			height: 100%;\r\n" + 
				"		  	background-position: center;\r\n" + 
				"		  	background-repeat: no-repeat;\r\n" + 
				"		  	background-attachment: fixed;\r\n" + 
				"		  	background-size: cover; \r\n" + 
				"		  	background-color: \">\r\n" + 
				"		  		<div style=\" font-size: 2em; color: rgba(237, 175, 111, 0.8);  \">\r\n" + 
				"		  			<br/>\r\n" + 
				"		  			<h1 style=\"background-color: black; \"> Librairie 7 </h1>\r\n" + 
				"		  			<br/>\r\n" + 
				"		  		</div>	  			\r\n" + 
				"	  		</div>\r\n" + 
				"	  	</header>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	  	<div style=\" margin-left: 6em; color: rgba(237, 175, 111, 1); \">\r\n" + 
				"		  	<h2 style=\" color: white ; font-size: 1.5em\">Il est temps ...</h1><br/>\r\n" + 
				"			<p >\r\n" + 
				"			 	Name,<br/>\r\n" + 
				"			</p>\r\n" + 
				"			<p>\r\n" + 
				"				Il semblerait que vous n'ayez toujours pas rendu votre livre ...\r\n" + 
				"			</p>\r\n" + 
				"			<br/>\r\n" + 
				"			<br/>\r\n" + 
				"			 	\r\n" + 
				"\r\n" + 
				"			\r\n" + 
				"\r\n" + 
				"	  	</div>\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"		 <footer>\r\n" + 
				"		 	<div align=\"center\" style=\"background-image: url('http://images.unsplash.com/photo-1533749047139-189de3cf06d3?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjEyMDd9');   \r\n" + 
				"			height: 100%;\r\n" + 
				"		  	background-position: center;\r\n" + 
				"		  	background-repeat: no-repeat;\r\n" + 
				"		  	background-attachment: fixed;\r\n" + 
				"		  	background-size: cover; \r\n" + 
				"		  	background-color: \">\r\n" + 
				"		  		<div style=\"height:8em;color: rgba(237, 175, 111, 1);\">\r\n" + 
				"		  			\r\n" + 
				"		  		</div>	  			\r\n" + 
				"	  		</div>\r\n" + 
				"\r\n" + 
				"		 </footer>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</html>";
				
		
		emailService.sendSimpleMessage("p.plaxine@orange.fr", "teste n.7 ", emailHtml);
		
		return null;
	}

}
