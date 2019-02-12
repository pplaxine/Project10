package com.philippe75.libraryWS.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;

import com.philippe75.libraryWS.consumer.contract.dao.BookDao;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Implements BookDto Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("bookDao")
public class BookDaoImpl extends AbstractDao implements BookDao {
	
	/**
	 * Get all the {@link Book} with name required.
	 * 
	 * @param name the name of the book
	 * @return List<Book> list of {@link Book} with name required.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getListBookByName(String name) throws Exception {
		
		String sql = "SELECT * FROM book WHERE name = :name";
		List<Book> listbook;
		
		Session session = getSession();
		session.beginTransaction();
		try {
		
			listbook = (List<Book>)session.createSQLQuery(sql).setParameter("name", name).addEntity(Book.class).list(); 
			session.getTransaction().commit();
			session.close();
		
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return listbook;
	}

	/**
	 * Method that gets, the 10 first book containing entry in their name.  
	 * 
	 * @param name the string to be contained in the book name
	 * @return List<Book> list of {@link Book} containing the entry in their name.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getListBookStartingBy(String name) throws Exception {
		
		String sql = "SELECT DISTINCT ON (name) * FROM book WHERE name ILIKE :name ORDER BY name LIMIT 10";
		List<Book> listbook;
		
		Session session = getSession();
		session.beginTransaction();
		try {
			
			listbook = (List<Book>)session.createSQLQuery(sql)
										.setParameter("name", name+"%")
										.addEntity(Book.class)
										.list();
			
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return listbook;
	}
	
	

	
}
