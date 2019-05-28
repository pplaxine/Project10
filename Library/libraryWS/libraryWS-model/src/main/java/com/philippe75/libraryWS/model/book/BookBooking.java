package com.philippe75.libraryWS.model.book;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Model object of a book booking.</b>
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
@Entity
@Table(name="book_booking")
public class BookBooking implements Comparable<BookBooking>{
	
	/**
	 * Unique id of the book booked.
	 */
	private Integer id;
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
	private boolean ended;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="book_name")
	@NotNull
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@Column(name="book_author")
	@NotNull
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	@ManyToOne
	@JoinColumn(name="user_account_id")
	@NotNull
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	@Column(name="mail_sent_date")
	public Date getMailSentDate() {
		return mailSentDate;
	}
	public void setMailSentDate(Date mailSentDate) {
		this.mailSentDate = mailSentDate;
	}
	@NotNull
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	@Override
	public int compareTo(BookBooking o) {
		return this.getId().compareTo(o.getId());
	}
}
