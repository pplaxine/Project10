package com.philippe75.env.business.dto;

import java.util.List;

import com.philippe75.env.model.Address;

public class UserTestDto {
	
	private int id;
	private String firstName;
	private String lastName;
	private List<Address> listAddress;
	private int ageFictif;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Address> getListAddress() {
		return listAddress;
	}
	public void setListAddress(List<Address> listAddress) {
		this.listAddress = listAddress;
	}
	public int getAgeFictif() {
		return ageFictif;
	}
	public void setAgeFictif(int ageFictif) {
		this.ageFictif = ageFictif;
	}
	
}
