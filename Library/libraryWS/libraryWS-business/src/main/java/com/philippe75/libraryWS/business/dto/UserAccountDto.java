package com.philippe75.libraryWS.business.dto;

import java.util.ArrayList;
import java.util.Collection;

import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.user.UserAddress;

/**
 * <b>Dto object of a user Account</b>
 * <p>
 * 	Used to transport only the necessary informations of a UserAccount object.   
 * </p>
 * 
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>an id</li>
 * <li>a member id</li>
 * <li>an authorization access</li>
 * <li>a first name</li>
 * <li>a sure name</li>
 * <li>an address</li>
 * <li>an email</li>
 * <li>a phone number</li>
 * <li>the account state</li>
 * <li> a list of borrowing</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class UserAccountDto {
	
	/**
	 * Unique id of a user for the database.
	 */
	private int id;
	/**
	 * Unique id given to user at inscription for login.
	 */
	private String userMemberId;
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
	 * Historic of all the borrowings of the user.
	 */
	private Collection<Borrowing> listBorrowing = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserMemberId() {
		return userMemberId;
	}
	public void setUserMemberId(String userMemberId) {
		this.userMemberId = userMemberId;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSureName() {
		return sureName;
	}
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isBlockedAccount() {
		return blockedAccount;
	}
	public void setBlockedAccount(boolean blockedAccount) {
		this.blockedAccount = blockedAccount;
	}
	public Collection<Borrowing> getListBorrowing() {
		return listBorrowing;
	}
	public void setListBorrowing(Collection<Borrowing> listBorrowing) {
		this.listBorrowing = listBorrowing;
	}
	
}
