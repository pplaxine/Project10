package com.philippe75.libraryWS.exposure.servlet;


import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.UserAccountDto;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.exception.AuthentificationException;
import com.philippe75.libraryWS.model.exception.DataBaseException;
import com.philippe75.libraryWS.model.exception.NotFoundException;

public class Main extends HttpServlet {

	public static final String VUE_MAIN ="/WEB-INF/main.jsp";
	
	@Inject
	private ManagerHandler managerHandler; 
	
	@Inject
	private DaoHandler daoHandler;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			UserAccountDto uad = managerHandler.getUserAccountManager().getUserAccountByMemberId("JTille", "t2est01");
			System.out.println("Cette persone existe !");
		
		} catch (AuthentificationException e) {
			System.out.println("Auth Exception haha ! " + e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
