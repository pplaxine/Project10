package com.philippe75.libraryWS.business.dto;

import java.util.Date;

import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Dto object of a book booking.</b>
 * 
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>an id</li>
 * <li>a book name</li>
 * <li>a book author</li>
 * <li>a user account</li>
 * <li>a date of mail sending</li>
 * <li>if booking as ended</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class BookBookingDto {
	/**
	 * Unique id of the book booked.
	 */
	private int id;
	/**
	 * The name of the book booked.
	 */
	private String bookName;
	/**
	 * The author of the book booked.
	 */
	private String bookAuthor;
	/**
	 * The member that booked the book.
	 */
	private UserAccount userAccount;
	/**
	 * When the mail of the availibility of the book has been went to the member.
	 */
	private Date mailSentDate;
	/**
	 * If the booking has expired / member borrowed the book
	 */
	private Boolean ended;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public Date getMailSentDate() {
		return mailSentDate;
	}
	public void setMailSentDate(Date mailSentDate) {
		this.mailSentDate = mailSentDate;
	}
	public Boolean getEnded() {
		return ended;
	}
	public void setEnded(Boolean ended) {
		this.ended = ended;
	}
	
	
}
