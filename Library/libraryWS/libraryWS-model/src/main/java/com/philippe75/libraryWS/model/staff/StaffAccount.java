package com.philippe75.libraryWS.model.staff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.philippe75.libraryWS.model.library.Library;

/**
 * <b>Model object of an Account of a Staff member</b>
 * 
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>an id</li>
 * <li>a login name</li>
 * <li>a password</li>
 * <li>an access</li>
 * <li>a first name</li>
 * <li>a sure name</li>
 * <li>an account state</li>
 * <li>a library</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity
@Table(name="staff_account")
public class StaffAccount {
	
	/**
	 * Unique id of a staff account .
	 */
	private int id;
	/**
	 * Login a the staff member.
	 */
	private String loginName;
	/**
	 * Password chosen for the account.
	 */
	private String password;
	/**
	 * Access rights attached to the account.
	 */
	private String access;
	/**
	 * First name of the staff member.
	 */
	private String firstName;
	/**
	 * Surname of the staff member.
	 */
	private String sureName;
	/**
	 * Account can be deactivated after staff member leave.
	 */
	private boolean accountActivated;
	/**
	 * Library to which staff member is attached to.
	 */
	private Library library;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="login_name")
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="surename")
	public String getSureName() {
		return sureName;
	}
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
	@Column(name="account_activated")
	public boolean isAccountActivated() {
		return accountActivated;
	}
	public void setAccountActivated(boolean accountActivated) {
		this.accountActivated = accountActivated;
	}
	@ManyToOne
	@JoinColumn(name="library_id")
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
	
}
