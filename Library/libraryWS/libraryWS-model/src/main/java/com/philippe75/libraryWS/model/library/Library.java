package com.philippe75.libraryWS.model.library;

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

import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.staff.StaffAccount;

@Entity
public class Library {
	
	private int id;
	private String name;
	private double phoneNumber;
	private LibraryAddress address;
	private Collection<StaffAccount> listStaffAccount = new ArrayList<>();
	private Collection<Book> listBook = new ArrayList<>();

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
	
	@OneToMany(mappedBy="library")
	public Collection<StaffAccount> getListStaffAccount() {
		return listStaffAccount;
	}
	public void setListStaffAccount(Collection<StaffAccount> listStaffAccount) {
		this.listStaffAccount = listStaffAccount;
	}
	
	@OneToMany(mappedBy="library")
	public Collection<Book> getListBook() {
		return listBook;
	}
	public void setListBook(Collection<Book> listBook) {
		this.listBook = listBook;
	}
	
	
	
	

}
