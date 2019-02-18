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

	/**
	 * Method get that creates new borrowing.  
	 * 
	 * @param borrowing the new borrowing.
	 */
	@Override
	public void createBorrowing(Borrowing borrowing) throws Exception {
		
		Session session = getSession();
		try {
			session.beginTransaction();
			session.save(borrowing);
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	/**
	 * Method that ends a borrowing when user returns a book.  
	 * 
	 * @param borrowing the borrowing comming to a end.
	 */
	@Override
	public void endBorrowing(Borrowing borrowing) throws Exception {
		String hql = "UPDATE Borrowing b set b.effectiveEndDate=:effectiveEndDate WHERE b.id= :borrowingId";
		
		Session session = getSession();
		session.beginTransaction();
		try {
			session.createQuery(hql)
						.setParameter("effectiveEndDate", borrowing.getEffectiveEndDate())
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

	/**
	 * Method that gets, all the borrowings with the either supposed end date or extended supposed end date overdue.  
	 * 
	 * @return List<Borrowing> list of {@link Borrowing}.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Borrowing> getAllLateBorrowings() throws Exception {
		String sql ="SELECT * FROM borrowing WHERE (CASE WHEN extended=false THEN supposed_end_date < NOW() WHEN extended=true THEN second_supposed_end_date<NOW() END) AND effective_end_date is null"; 
		
		List<Borrowing> listborrowing;
		
		Session session = getSession();
		session.beginTransaction();
		try {
			
			listborrowing = (List<Borrowing>)session.createSQLQuery(sql)	
													.addEntity(Borrowing.class)
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
	

