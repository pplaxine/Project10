package com.philippe75.libraryWS.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_account")
public class UserAccount {
	
	private int id;
	private String userMemberId;
	private String password;
	private String access;
	private String firstName;
	private String sureName;
	private UserAddress address;
	private String email;
	private double phoneNumber;
	private boolean blockedAccount;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="user_member_id")
	public String getUserMemberId() {
		return userMemberId;
	}
	public void setUserMemberId(String userMemberId) {
		this.userMemberId = userMemberId;
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
	
	@OneToOne
	@JoinColumn(name="user_address_id")
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
	@Column(name="phone_number",columnDefinition="NUMERIC(15)")
	public double getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name="blocked_account")
	public boolean isBlockedAccount() {
		return blockedAccount;
	}
	public void setBlockedAccount(boolean blockedAccount) {
		this.blockedAccount = blockedAccount;
	}

	
	
	
	
	
	
}
