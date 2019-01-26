package com.philippe75.libraryWS.model.book;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.philippe75.libraryWS.model.user.UserAccount;

@Entity
public class Borrowing {
	
	private int id;
	private Date startDate;
	private Date supposedEndDate;
	private Date secondSupposedEndDate;
	private Date effectiveEndDate;
	private boolean extended;
	private Book book;
	private UserAccount userAccount;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="supposed_end_date")
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
	public boolean isExtended() {
		return extended;
	}
	public void setExtended(boolean extended) {
		this.extended = extended;
	}
	@ManyToOne
	@JoinColumn(name="book_id")
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@ManyToOne
	@JoinColumn(name="user_account_id")
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
}
