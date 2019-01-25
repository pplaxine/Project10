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

@Entity
@Table(name="staff_account")
public class StaffAccount {

	private int id;
	private String loginName;
	private String password;
	private String access;
	private String firstName;
	private String sureName;
	private boolean accountActivated;
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
