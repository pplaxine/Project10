
package com.philippe75.libraryWebapp.stub.generated.borrowingServ;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour userAccount complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="userAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="access" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://borrowing.service.exposure.libraryWS.philippe75.com/}userAddress" minOccurs="0"/>
 *         &lt;element name="blockedAccount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="listBorrowing" type="{http://borrowing.service.exposure.libraryWS.philippe75.com/}borrowing" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userMemberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userAccount", propOrder = {
    "access",
    "address",
    "blockedAccount",
    "email",
    "firstName",
    "id",
    "listBorrowing",
    "password",
    "phoneNumber",
    "sureName",
    "userMemberId"
})
public class UserAccount {

    protected String access;
    protected UserAddress address;
    protected boolean blockedAccount;
    protected String email;
    protected String firstName;
    protected int id;
    @XmlElement(nillable = true)
    protected List<Borrowing> listBorrowing;
    protected String password;
    protected double phoneNumber;
    protected String sureName;
    protected String userMemberId;

    /**
     * Obtient la valeur de la propriété access.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccess() {
        return access;
    }

    /**
     * Définit la valeur de la propriété access.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccess(String value) {
        this.access = value;
    }

    /**
     * Obtient la valeur de la propriété address.
     * 
     * @return
     *     possible object is
     *     {@link UserAddress }
     *     
     */
    public UserAddress getAddress() {
        return address;
    }

    /**
     * Définit la valeur de la propriété address.
     * 
     * @param value
     *     allowed object is
     *     {@link UserAddress }
     *     
     */
    public void setAddress(UserAddress value) {
        this.address = value;
    }

    /**
     * Obtient la valeur de la propriété blockedAccount.
     * 
     */
    public boolean isBlockedAccount() {
        return blockedAccount;
    }

    /**
     * Définit la valeur de la propriété blockedAccount.
     * 
     */
    public void setBlockedAccount(boolean value) {
        this.blockedAccount = value;
    }

    /**
     * Obtient la valeur de la propriété email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit la valeur de la propriété email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtient la valeur de la propriété firstName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Définit la valeur de la propriété firstName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the listBorrowing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listBorrowing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListBorrowing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Borrowing }
     * 
     * 
     */
    public List<Borrowing> getListBorrowing() {
        if (listBorrowing == null) {
            listBorrowing = new ArrayList<Borrowing>();
        }
        return this.listBorrowing;
    }

    /**
     * Obtient la valeur de la propriété password.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit la valeur de la propriété password.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Obtient la valeur de la propriété phoneNumber.
     * 
     */
    public double getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Définit la valeur de la propriété phoneNumber.
     * 
     */
    public void setPhoneNumber(double value) {
        this.phoneNumber = value;
    }

    /**
     * Obtient la valeur de la propriété sureName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSureName() {
        return sureName;
    }

    /**
     * Définit la valeur de la propriété sureName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSureName(String value) {
        this.sureName = value;
    }

    /**
     * Obtient la valeur de la propriété userMemberId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserMemberId() {
        return userMemberId;
    }

    /**
     * Définit la valeur de la propriété userMemberId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserMemberId(String value) {
        this.userMemberId = value;
    }

}
