package com.philippe75.env.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.env.business.contract.handler.ManagerHandler;
import com.philippe75.env.business.dto.UserTestDto;
import com.philippe75.env.consumer.contract.handler.DaoHandler;
import com.philippe75.env.model.Address;
import com.philippe75.env.model.UserTest;

public class Main extends HttpServlet {

	public static final String VUE_MAIN ="/WEB-INF/main.jsp";
	
	@Inject
	ManagerHandler managerHandler;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserTestDto utd = managerHandler.getUserTestManager().getUserById(4);
	
		System.out.println(utd.getAgeFictif());
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
