package com.philippe75.env.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vehicule {
	
	private int Id; 
	private String name;
	private UserTest userTest;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne								//permet de retrouver UserTest du véhicule (ne pas oublié save(userTest)! 
	@JoinColumn(name="nouvelle_colone")
	public UserTest getUserTest() {
		return userTest;
	}
	public void setUserTest(UserTest userTest) {
		this.userTest = userTest;
	}
	
	
	
}
