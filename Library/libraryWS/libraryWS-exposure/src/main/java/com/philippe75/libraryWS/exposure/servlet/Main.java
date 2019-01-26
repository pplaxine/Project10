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
import com.philippe75.libraryWS.model.book.Borrowing;

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

		
		Borrowing bor = (Borrowing)session.get(Borrowing.class, 1);
		System.out.println(bor.getBook().getName());
		System.out.println(bor.getUserAccount().getFirstName());
		Book book = (Book)session.get(Book.class, 1);
		
		Collection<Borrowing> listbo = book.getListBorrowing();
		System.out.println("Libre : "+ book.getName());
		for (Borrowing borrowing : listbo) {
			System.out.println("Location le :" + borrowing.getStartDate() + " par : " + borrowing.getUserAccount().getFirstName() + " " + borrowing.getUserAccount().getSureName());
		}
		session.getTransaction().commit();

		
		
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
