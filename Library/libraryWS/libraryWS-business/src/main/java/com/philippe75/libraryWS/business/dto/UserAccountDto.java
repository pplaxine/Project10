package com.philippe75.libraryWS.business.dto;

import java.util.ArrayList;
import java.util.Collection;

import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.user.UserAddress;

public class UserAccountDto {

	private int id;
	private String userMemberId;
	private String access;
	private String firstName;
	private String sureName;
	private UserAddress address;
	private String email;
	private double phoneNumber;
	private boolean blockedAccount;
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
