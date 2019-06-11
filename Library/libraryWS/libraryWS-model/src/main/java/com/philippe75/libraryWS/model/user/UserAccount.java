package com.philippe75.libraryWS.model.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.philippe75.libraryWS.model.book.BookBooking;
import com.philippe75.libraryWS.model.book.Borrowing;

/**
 * <b>Model object of an Account of a user member of the Library</b>
 * 
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>an id</li>
 * <li>a member id</li>
 * <li>a password</li>
 * <li>an authorization access</li>
 * <li>a first name</li>
 * <li>a sure name</li>
 * <li>an address</li>
 * <li>an email</li>
 * <li>a phone number</li>
 * <li>the account state</li>
 * <li>the mail reminder status</li>
 * <li> a list of borrowing</li>
 * <li> a list of booking</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity
@Table(name="user_account")
public class UserAccount {
	
	/**
	 * Unique id of a user for the database.
	 */
	private int id;
	/**
	 * Unique id given to user at inscription for login.
	 */
	private String userMemberId;
	/**
	 * Password chosen for the account.
	 */
	private String password;
	/**
	 * Access rights attached to the account(right/no rights/partial rights).
	 */
	private String access;
	/**
	 * First name of the user.
	 */
	private String firstName;
	/**
	 * Surname of the user.
	 */
	private String sureName;
	/**
	 * Address of the user.
	 */
	private UserAddress address;
	/**
	 * Email of the user.
	 */
	private String email;
	/**
	 * users phone number.
	 */
	private double phoneNumber;
	/**
	 * Account can be blocked if a borrowed book is not given back on time.
	 */
	private boolean blockedAccount;
	/**
	 * Is true if user wants to receive a reminder mail about its about to end borrowings.
	 */
	private boolean mailReminder;
	/**
	 * Historic of all the borrowings of the user.
	 */
	private Collection<Borrowing> listBorrowing = new ArrayList<>();
	/**
	 * Historic of all the bookings of the user.
	 */
	private Collection<BookBooking> listBookBooking = new ArrayList<>();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="user_member_id")
	@NotNull
	@Size(min=4,max=20)
	public String getUserMemberId() {
		return userMemberId;
	}
	public void setUserMemberId(String userMemberId) {
		this.userMemberId = userMemberId;
	}
	@Size(min=4,max=70)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	@Column(name="firstname")
	@NotNull
	@Size(min=1,max=50)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="surename")
	@NotNull
	@Size(min=1,max=50)
	public String getSureName() {
		return sureName;
	}
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
	
	@OneToOne
	@JoinColumn(name="user_address_id")
	@NotNull
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	@NotNull
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="phone_number",columnDefinition="NUMERIC(15)")
	public double getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name="blocked_account")
	@NotNull
	public boolean isBlockedAccount() {
		return blockedAccount;
	}
	public void setBlockedAccount(boolean blockedAccount) {
		this.blockedAccount = blockedAccount;
	}
	@Column(name="mail_reminder")
	@NotNull
	public boolean isMailReminder() {
		return mailReminder;
	}
	public void setMailReminder(boolean mailReminder) {
		this.mailReminder = mailReminder;
	}
	@OneToMany(mappedBy="userAccount")
	@Transient
	public Collection<Borrowing> getListBorrowing() {
		return listBorrowing;
	}
	public void setListBorrowing(Collection<Borrowing> listBorrowing) {
		this.listBorrowing = listBorrowing;
	}
	@OneToMany(mappedBy="userAccount")
	@Transient
	public Collection<BookBooking> getListBookBooking() {
		return listBookBooking;
	}
	public void setListBookBooking(Collection<BookBooking> listBookBooking) {
		this.listBookBooking = listBookBooking;
	}
	
	
	
	
	
}
