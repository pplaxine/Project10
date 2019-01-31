
package com.philippe75.libraryWebapp.stub.generated.authServ;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AuthentificationException complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AuthentificationException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fault" type="{http://authentification.service.exposure.libraryWS.philippe75.com/}authentificationFault" minOccurs="0"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthentificationException", propOrder = {
    "fault",
    "message"
})
public class AuthentificationException {

    protected AuthentificationFault fault;
    protected String message;

    /**
     * Obtient la valeur de la propriété fault.
     * 
     * @return
     *     possible object is
     *     {@link AuthentificationFault }
     *     
     */
    public AuthentificationFault getFault() {
        return fault;
    }

    /**
     * Définit la valeur de la propriété fault.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthentificationFault }
     *     
     */
    public void setFault(AuthentificationFault value) {
        this.fault = value;
    }

    /**
     * Obtient la valeur de la propriété message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Définit la valeur de la propriété message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
