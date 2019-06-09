
package com.philippe75.libraryBatch.stub.generated.borrowingServ;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour bookBookingDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="bookBookingDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bookAuthor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ended" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mailSentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="userAccount" type="{http://borrowing.service.exposure.libraryWS.philippe75.com/}userAccount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookBookingDto", propOrder = {
    "bookAuthor",
    "bookName",
    "ended",
    "id",
    "mailSentDate",
    "userAccount"
})
public class BookBookingDto {

    protected String bookAuthor;
    protected String bookName;
    protected Boolean ended;
    protected Integer id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mailSentDate;
    protected UserAccount userAccount;

    /**
     * Obtient la valeur de la propriété bookAuthor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * Définit la valeur de la propriété bookAuthor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookAuthor(String value) {
        this.bookAuthor = value;
    }

    /**
     * Obtient la valeur de la propriété bookName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Définit la valeur de la propriété bookName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookName(String value) {
        this.bookName = value;
    }

    /**
     * Obtient la valeur de la propriété ended.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnded() {
        return ended;
    }

    /**
     * Définit la valeur de la propriété ended.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnded(Boolean value) {
        this.ended = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété mailSentDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMailSentDate() {
        return mailSentDate;
    }

    /**
     * Définit la valeur de la propriété mailSentDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMailSentDate(XMLGregorianCalendar value) {
        this.mailSentDate = value;
    }

    /**
     * Obtient la valeur de la propriété userAccount.
     * 
     * @return
     *     possible object is
     *     {@link UserAccount }
     *     
     */
    public UserAccount getUserAccount() {
        return userAccount;
    }

    /**
     * Définit la valeur de la propriété userAccount.
     * 
     * @param value
     *     allowed object is
     *     {@link UserAccount }
     *     
     */
    public void setUserAccount(UserAccount value) {
        this.userAccount = value;
    }

}
