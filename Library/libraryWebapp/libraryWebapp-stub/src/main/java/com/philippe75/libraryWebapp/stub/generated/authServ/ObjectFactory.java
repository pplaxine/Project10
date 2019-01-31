
package com.philippe75.libraryWebapp.stub.generated.authServ;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.philippe75.libraryWebapp.stub.generated.authServ package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AuthentificationException_QNAME = new QName("http://authentification.service.exposure.libraryWS.philippe75.com/", "AuthentificationException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.philippe75.libraryWebapp.stub.generated.authServ
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthentificationException }
     * 
     */
    public AuthentificationException createAuthentificationException() {
        return new AuthentificationException();
    }

    /**
     * Create an instance of {@link UserAddress }
     * 
     */
    public UserAddress createUserAddress() {
        return new UserAddress();
    }

    /**
     * Create an instance of {@link AuthentificationFault }
     * 
     */
    public AuthentificationFault createAuthentificationFault() {
        return new AuthentificationFault();
    }

    /**
     * Create an instance of {@link UserAccountDto }
     * 
     */
    public UserAccountDto createUserAccountDto() {
        return new UserAccountDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthentificationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://authentification.service.exposure.libraryWS.philippe75.com/", name = "AuthentificationException")
    public JAXBElement<AuthentificationException> createAuthentificationException(AuthentificationException value) {
        return new JAXBElement<AuthentificationException>(_AuthentificationException_QNAME, AuthentificationException.class, null, value);
    }

}
