package com.philippe75.libraryWS.business.dto;

import java.util.Date;

import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Model object of Borrowing.</b>
 * 
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>an id</li>
 * <li>a start date</li>
 * <li>a supposed end date</li>
 * <li>a second supposed end date</li>
 * <li>an effective end date</li>
 * <li>an extension of borrowing</li>
 * <li>a book</li>
 * <li>a user account</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class BorrowingDto {
	
	/**
	 * Unique id of the borrowing.
	 */
	private int id;
	/**
	 * When book get borrowed.
	 */
	private Date startDate;
	/**
	 * When book needs to be brought back.
	 */
	private Date supposedEndDate;
	/**
	 * If borrowing get extended, new date when book needs to be brought back.
	 */
	private Date secondSupposedEndDate;
	/**
	 * When book is actually given back.
	 */
	private Date effectiveEndDate;
	/**
	 * True if member decided to extend the borrowing (available only once).
	 */
	private boolean extended;
	/**
	 * Book borrowed.
	 */
	private Book book;
	/**
	 * Member that made the borrowing
	 */
	private UserAccount userAccount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getSupposedEndDate() {
		return supposedEndDate;
	}
	public void setSupposedEndDate(Date supposedEndDate) {
		this.supposedEndDate = supposedEndDate;
	}
	public Date getSecondSupposedEndDate() {
		return secondSupposedEndDate;
	}
	public void setSecondSupposedEndDate(Date secondSupposedEndDate) {
		this.secondSupposedEndDate = secondSupposedEndDate;
	}
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
	public boolean isExtended() {
		return extended;
	}
	public void setExtended(boolean extended) {
		this.extended = extended;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
}
