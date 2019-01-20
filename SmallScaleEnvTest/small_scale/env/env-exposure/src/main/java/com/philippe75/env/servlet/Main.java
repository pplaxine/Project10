package com.philippe75.env.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.env.business.contract.handler.ManagerHandler;
import com.philippe75.env.business.dto.UserTestDto;
import com.philippe75.env.consumer.impl.dao.AddressDaoImpl;
import com.philippe75.env.model.Address;
import com.philippe75.env.model.UserTest;
import com.sun.mail.util.UUDecoderStream;

public class Main extends HttpServlet {

	public static final String VUE_MAIN ="/WEB-INF/main.jsp";
	
	@Inject
	private ManagerHandler managerHandler;
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Collection<Address> listAddress = managerHandler.getUserTestManager().getAllUser().get(0).getListAddress();
		Collection<Address> listAddressDateAdded = new ArrayList<>();
		for (Address address : listAddress) {
			address.setDateCreation(new Date());
			listAddressDateAdded.add(address);
		}
		
		
		Address address = managerHandler.getUserTestManager().getAllUser().get(0).getListAddress().iterator().next();
		address.setDateCreation((new Date()));
		
		Address address2 = managerHandler.getUserTestManager().getAllUser().get(0).getListAddress().iterator().next();
		address2.setDateCreation((new Date()));
		
		
		UserTest ut = new UserTest();
		ut.setFirstName("Louis ");
		ut.setLastName("Le Grand");
		ut.setAge(48);
		ut.setHomeAddress(address2);
		ut.setOfficeAddress(address);
		ut.setListAddress(listAddressDateAdded);
		

		
		//SAVE
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(address);
//		session.save(address2);
//		session.getTransaction().commit();
		
		//GET
//		session = sessionFactory.openSession();
//		session.beginTransaction();
//		address = (Address)session.get(Address.class, 1);
//		System.out.println(address.getStreetName() + " " + address.getCity() ); 
//		address = (Address)session.get(Address.class, 2);
//		System.out.println(address.getStreetName() + " " + address.getCity() );
		
		//SAVE WITH EMBEDDED OBJECT 
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(ut);
		session.getTransaction().commit();
		
		
		
//		List<UserTestDto> list = managerHandler.getUserTestManager().getAllUser();
//		for (UserTestDto utd : list) {
//			String street;
//			String city;
//			System.out.println(utd.getFirstName() + " " + utd.getLastName() + " à " + utd.getAgeFictif() + " ans et possède les adresses suivante : " );
//			
//			for (Address address : utd.getListAddress()) {
//				street = address.getStreetName();
//				city = address.getCity();
//				System.out.println("Nom de la rue : " + street + " à " + city);
//			}
//		}
		

		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
