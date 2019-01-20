package com.philippe75.env.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Embeddable
@Table(name="adress_plus")
public class Address {
	
	//NE PAS METTRE d'id si simple object embbeded (id g�n�r� par hibernate et donc doublon sur le nom) 
	//private int id;
	private String streetNumber;
	private String streetName;
	private String city;
	private int postCode;
	private Date dateCreation;


//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	@Column(name="street_number")
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	@Column(name="street_name")
	//@Lob 				//CLOB (Charactere Large OBject) par defaut / BLOB (Byte Large OBject) si annotation @Lob  (emp�che limite de 255 cactere de base) // (info stock� avec chiffre)  
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	@Basic		//m�me que val par d�faut (n�c�ssaire seulement pour passer option)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Transient
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	@Temporal(TemporalType.DATE)
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
}
