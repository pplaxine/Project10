package com.philippe75.libraryWS.consumer.impl.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;
import com.philippe75.libraryWS.consumer.contract.dao.BorrowingDao;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.user.UserAccount;

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
	
	/**
	 * Method that gets a borrowing object with its id.  
	 * 
	 * @param borrowingId the borrowing id.
	 * @return Borrowing the borrowing object corresponding to the id.
	 */
	@Override
	public Borrowing getBorrowingById(Integer borrowingId) throws Exception {
		Borrowing  borrowing = null;
		String sql = "SELECT * FROM borrowing WHERE id = :borrowingId";
		
		Session session = getSession();
		session.beginTransaction();
		try {
			
			borrowing = (Borrowing) session.createSQLQuery(sql)	
												.setParameter("borrowingId", borrowingId)
												.addEntity(Borrowing.class)
												.getSingleResult();
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return borrowing;
	}

	/**
	 * Method that extend borrowing supposed end date.  
	 * 
	 * @param Borrowing the borrowing to update.
	 */
	@Override
	public void extendBorrowing(Borrowing borrowing) throws Exception {
		String hql = "UPDATE Borrowing b set b.extended=:extended, b.secondSupposedEndDate= :extendedDate WHERE b.id= :borrowingId";
		
		Session session = getSession();
		session.beginTransaction();
		try {
			session.createQuery(hql)
						.setParameter("extended", true)
						.setParameter("extendedDate", borrowing.getSecondSupposedEndDate())
						.setParameter("borrowingId", borrowing.getId())
						.executeUpdate();
			
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}


		
}
	

