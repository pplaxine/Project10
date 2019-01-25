package com.philippe75.libraryWS.exposure.servlet;


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

import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.Genre;
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
		
		Book book1 = new Book();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//session.save(libAd);
		
		Library lib = session.get(Library.class, 2);
		
//		book1.setName("Le tour de France");
//		book1.setAuthor("Tortue");
//		book1.setSummary("du contenu");
//		book1.setAvailable(true);
//		book1.setGenre(Genre.TRAGEDY);
//		book1.setLibrary(lib);
//		session.save(book1);
		
		Book book = session.get(Book.class, 2);
		System.out.println(book.getGenre());
		session.getTransaction().commit();

		
		
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
