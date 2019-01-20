package com.philippe75.env.servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.env.business.contract.handler.ManagerHandler;
import com.philippe75.env.business.dto.UserTestDto;
import com.philippe75.env.model.Address;

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
		
		List<UserTestDto> list = managerHandler.getUserTestManager().getAllUser();
		for (UserTestDto utd : list) {
			String street;
			String city;
			System.out.println(utd.getFirstName() + " " + utd.getLastName() + " à " + utd.getAgeFictif() + " ans et possède les adresses suivante : " );
			
			for (Address address : utd.getListAddress()) {
				street = address.getStreetName();
				city = address.getCity();
				System.out.println("Nom de la rue : " + street + " à " + city);
			}
		}
		
		
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
