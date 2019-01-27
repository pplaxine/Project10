package com.philippe75.libraryWS.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;

import com.philippe75.libraryWS.consumer.contract.dao.BookDao;
import com.philippe75.libraryWS.model.book.Book;

@Named("bookDao")
public class BookDaoImpl extends AbstractDao implements BookDao {

	@Override
	public List<Book> getListBookByName(String name) {
		
		Session session = getSession();
		session.beginTransaction();
		List<Book> listBook = (List<Book>)session.createSQLQuery("SELECT * FROM book WHERE name = :name").setParameter("name", name).addEntity(Book.class).list(); 
		session.getTransaction().commit();
		session.close();
		
		return listBook;
	}
	
	

	
}
