package com.philippe75.libraryWS.exposure.servlet;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.model.book.BookGenre;
import com.philippe75.libraryWS.model.library.Library;
import com.philippe75.libraryWS.model.library.LibraryAddress;
import com.philippe75.libraryWS.model.staff.StaffAccount;
import com.philippe75.libraryWS.model.user.UserAccount;
import com.philippe75.libraryWS.model.user.UserAddress;

public class Main extends HttpServlet {

	public static final String VUE_MAIN ="/WEB-INF/main.jsp";
	
	@Inject
	private SessionFactory sessionFactory;
			
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LibraryAddress libAd = new LibraryAddress();
		libAd.setStreetNumber("18");
		libAd.setStreetName("rue des Pompiers");
		libAd.setCity("Toulouse");
		libAd.setPostCode(31000);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//session.save(libAd);
		

		
		session.getTransaction().commit();

		
		
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
