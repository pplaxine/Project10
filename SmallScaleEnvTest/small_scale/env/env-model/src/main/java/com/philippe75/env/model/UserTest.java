package com.philippe75.env.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="user_test")
public class UserTest {
	
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private Address homeAddress;
	private Address officeAddress;
	private Collection<Address> listAddress = new ArrayList<>();		//List<> allow duplicate + has index , Set<> not + has no index 	// implementation de l'interface 
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Embedded 	//not mandatory (already indicated by annotation @Embeddebale on Address class) 
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	@Embedded
	//permet de configurer les att de l'objet address (car si un objet contient en att 2 fois même autre objet, les nom les att de chacune instance de ces objet = doublons dans nom colon en BDD   
	@AttributeOverrides({			//Firest is AttributeOverrideS <===== with s and is englobing all the others
	@AttributeOverride(name="streetNumber", column=@Column(name="street_number_office")),
	@AttributeOverride(name="streetName", column=@Column(name="street_name_office")),
	@AttributeOverride(name="city", column=@Column(name="city_office")),
	@AttributeOverride(name="dateCreation", column=@Column(name="date_creation_office"))})
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	
	@ElementCollection
	@JoinTable(name="userTest_address", joinColumns=@JoinColumn(name="userTest_id"))						//annotation pour configurer la table jointe // nom la column jointe (ici id joint les deux tables)
	@GenericGenerator(name="sequencegen", strategy="sequence")														// annotation specific to hibernate												
	@CollectionId(columns={@Column(name="id")}, generator="sequencegen",type=@Type(type="integer"))			// annotation specific to hibernate (and not JPA standart implementation) permet de definir un index (id) pour la table jointe 
	public Collection<Address> getListAddress() {
		return listAddress;
	}
	public void setListAddress(Collection<Address> listAddress) {
		this.listAddress = listAddress;
	} 

}
