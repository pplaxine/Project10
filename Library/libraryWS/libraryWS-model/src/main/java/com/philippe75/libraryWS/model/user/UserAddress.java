package com.philippe75.libraryWS.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <b>Model object of the address attached to a user</b>
 * 
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>an id</li>
 * <li>a street number</li>
 * <li>a street name</li>
 * <li>a city</li>
 * <li>a post code</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity
@Table(name="user_address")
public class UserAddress {
	
	/**
	 * Unique id of the address of a user.
	 */
	private int id;
	/**
	 * Street number of the address.
	 */
	private String streetNumber;
	/**
	 * Street name of the address.
	 */
	private String streetName;
	/**
	 * City of the address.
	 */
	private String city;
	/**
	 * Post code of the address.
	 */
	private int postCode;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="street_number")
	@NotNull
	@Size(min=1,max=10)
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	@Column(name="street_name")
	@NotNull
	@Size(min=1,max=200)
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	@Column(name="city")
	@NotNull
	@Size(min=1,max=200)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="post_code",columnDefinition="NUMERIC")
	@NotNull
	@Size(min=1,max=5)
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	

}
