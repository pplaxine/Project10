package com.philippe75.libraryWS.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;
import com.philippe75.libraryWS.consumer.contract.dao.BorrowingDao;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.Borrowing;

/**
 * <b>Implements BorrowingDto Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("borrowingDao")
public class BorrowingDaoImpl extends AbstractDao implements BorrowingDao {
	
	/**
	 * Method that gets, all the borrowings of a user.  
	 * 
	 * @param userMemeberId the member id of a user
	 * @return List<Borrowing> list of {@link Borrowing} of a user.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Borrowing> getAllBorrowingForUser(String userMemberId) throws Exception {
		
		String hql = "SELECT b FROM Borrowing b JOIN b.userAccount as ua where ua.userMemberId = :userMemberId";
		List<Borrowing> listborrowing;
		
		Session session = getSession();
		session.beginTransaction();
		try {
			
			listborrowing = (List<Borrowing>)session.createQuery(hql)
													.setParameter("userMemberId", userMemberId)
													.list(); 
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return listborrowing;
	}
	
}
