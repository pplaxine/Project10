package com.philippe75.libraryWS.model.library;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.philippe75.libraryWS.model.staff.StaffAccount;

/**
 * <b>Model object of Library.</b>
 * 
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>an id</li>
 * <li>a name</li>
 * <li>a phone number</li>
 * <li>an address</li>
 * <li>a list of staff accounts</li>
 * <li>a list of books</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {
	
	/**
	 * Unique id of the Library.
	 */
	private int id;
	/**
	 * Library Name.
	 */
	private String name;
	/**
	 * Library Phone number.
	 */
	private double phoneNumber;
	/**
	 * Library address.
	 */
	private LibraryAddress address;
	/**
	 * Accounts of Staff members attached to the library(City has several libraries).
	 */
	@XmlTransient
	private Collection<StaffAccount> listStaffAccount = new ArrayList<>();
	/**
	 * List of all the books owned by library.
	 */
	//private Collection<Book> listBook = new ArrayList<>();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="phone_number",columnDefinition="NUMERIC(15)")
	public double getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@OneToOne
	@JoinColumn(name="library_address_id")
	public LibraryAddress getAddress() {
		return address;
	}
	public void setAddress(LibraryAddress address) {
		this.address = address;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="library")
	public Collection<StaffAccount> getListStaffAccount() {
		return listStaffAccount;
	}
	public void setListStaffAccount(Collection<StaffAccount> listStaffAccount) {
		this.listStaffAccount = listStaffAccount;
	}
	
//	@OneToMany(mappedBy="library")
//	public Collection<Book> getListBook() {
//		return listBook;
//	}
//	public void setListBook(Collection<Book> listBook) {
//		this.listBook = listBook;
//	}
	
	
	
	

}
