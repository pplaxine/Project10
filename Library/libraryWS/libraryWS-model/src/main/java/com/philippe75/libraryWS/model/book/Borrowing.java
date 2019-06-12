package com.philippe75.libraryWS.model.book;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Model object of a borrowing.</b>
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
@Entity
public class Borrowing {
	
	/**
	 * Unique id of the borrowing.
	 */
	private Integer id;
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
	 * True if a reminder mail for the borrowing comming to an end has been sent.
	 */
	private boolean reminderMailSent;
	/**
	 * Book borrowed.
	 */
	private Book book;
	/**
	 * Member that made the borrowing
	 */
	private UserAccount userAccount;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="start_date")
	@NotNull
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="supposed_end_date")
	@NotNull
	public Date getSupposedEndDate() {
		return supposedEndDate;
	}
	public void setSupposedEndDate(Date supposedEndDate) {
		this.supposedEndDate = supposedEndDate;
	}
	@Column(name="second_supposed_end_date")
	public Date getSecondSupposedEndDate() {
		return secondSupposedEndDate;
	}
	public void setSecondSupposedEndDate(Date secondSupposedEndDate) {
		this.secondSupposedEndDate = secondSupposedEndDate;
	}
	@Column(name="effective_end_date")
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}
	@NotNull
	public boolean isExtended() {
		return extended;
	}
	public void setExtended(boolean extended) {
		this.extended = extended;
	}
	@Column(name="reminder_mail_sent")
	@NotNull
	public boolean isReminderMailSent() {
		return reminderMailSent;
	}
	public void setReminderMailSent(boolean reminderMailSent) {
		this.reminderMailSent = reminderMailSent;
	}
	@ManyToOne
	@JoinColumn(name="book_id")
	@NotNull
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
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
	
	
	
	
}
