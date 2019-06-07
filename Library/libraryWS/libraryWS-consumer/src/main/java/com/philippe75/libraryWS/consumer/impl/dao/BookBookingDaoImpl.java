package com.philippe75.libraryWS.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Session;

import com.philippe75.libraryWS.consumer.contract.dao.BookBookingDao;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.BookBooking;

@Named("bookBookingDao")
@SuppressWarnings("unchecked")
public class BookBookingDaoImpl extends AbstractDao implements BookBookingDao {
	
	/**
	 * Method that gets, the waiting list of members for a book.  
	 * 
	 * @param book the book.
	 * 
	 * @return List<BookBooking> list of {@link BookBooking} for all copies of this book.
	 */
	@Override
	public List<BookBooking> getAllBookingsForABook(Book book) throws Exception{
		String hql = "FROM BookBooking WHERE bookName =:bookName AND bookAuthor =:bookAuthor" ;
		List<BookBooking> listBookBooking;
		
		Session session = getSession();
		try {
			session.beginTransaction();
			listBookBooking = (List<BookBooking>)session.createQuery(hql)
													.setParameter("bookName", book.getName())
													.setParameter("bookAuthor", book.getAuthor())
													.list(); 
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return listBookBooking;
	}
	
	/**
	 * Method that gets, all active bookings.  
	 * 
	 * @return List<BookBooking> list of all active {@link BookBooking}.
	 */
	@Override
	public List<BookBooking> getAllNotEndedBookings() throws Exception {
		String hql = "FROM BookBooking WHERE ended =:ended" ;
		List<BookBooking> listBookBooking;
		
		Session session = getSession();
		try {
			session.beginTransaction();
			listBookBooking = (List<BookBooking>)session.createQuery(hql)
													.setParameter("ended", false)
													.list(); 
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return listBookBooking;
	}
	
	/**
	 * Method that gets, all the bookings of a members.  
	 * 
	 * @param String user member id.
	 * 
	 * @return List<BookBooking> list of all {@link BookBooking} for a user.
	 */
	@Override
	public List<BookBooking> getAllBookingsForMember(String userMemberId) throws Exception {
		String hql = "SELECT b FROM BookBooking b JOIN b.userAccount as ua where ua.userMemberId = :userMemberId";
		List<BookBooking> listBookBooking;
		
		Session session = getSession();
		try {
			session.beginTransaction();
			
			listBookBooking = (List<BookBooking>)session.createQuery(hql)
														.setParameter("userMemberId", userMemberId)
														.list(); 
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return listBookBooking;
	}

	/**
	 * Method that add a booking to the booking queue of a book.  
	 * 
	 * @param bookBooking the Booking to be added in queue.
	 * 
	 * @return Integer Id of {@link BookBooking} newly persisted.
	 */
	@Override
	public int createBookBooking(BookBooking bookBooking) throws Exception {
		int id;
		
		Session session = getSession();
		try {
			session.beginTransaction();
			session.save(bookBooking);
			session.getTransaction().commit();
			
			session.close();
			id = bookBooking.getId();
			
			
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return id;
	}

	@Override
	public void endBookBooking(int bookBookingId) throws Exception {
		String hql = "UPDATE BookBooking b set b.ended =:ended WHERE b.id =:bookBookingId";
		
		Session session = getSession();
		try {
			session.beginTransaction();
			session.createQuery(hql)
						.setParameter("bookBookingId", bookBookingId)
						.setParameter("ended", true)
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
