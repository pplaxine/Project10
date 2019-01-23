package com.philippe75.env.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Chien {
	
	private int Id; 
	private String name;
	private Collection<UserTest> listUserTest = new ArrayList<>();
	
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
	
	@ManyToMany(mappedBy="listChien")		//pour avoir 1 seul table conjointe (on indique que jointure sur l'att listChien de l'autre objet) donc pas de mapping necessaire
	public Collection<UserTest> getListUserTest() {
		return listUserTest;
	}
	public void setListUserTest(Collection<UserTest> listUserTest) {
		this.listUserTest = listUserTest;
	}
	
	
	

	
	
}
