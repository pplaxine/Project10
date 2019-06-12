
package com.philippe75.libraryBatch.stub.generated.borrowingServ;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.philippe75.libraryBatch.stub.generated.borrowingServ package. 
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

    private final static QName _LibraryServiceException_QNAME = new QName("http://borrowing.service.exposure.libraryWS.philippe75.com/", "LibraryServiceException");
    private final static QName _Exception_QNAME = new QName("http://borrowing.service.exposure.libraryWS.philippe75.com/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.philippe75.libraryBatch.stub.generated.borrowingServ
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LibraryServiceException }
     * 
     */
    public LibraryServiceException createLibraryServiceException() {
        return new LibraryServiceException();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link ListOfBorrowingDto }
     * 
     */
    public ListOfBorrowingDto createListOfBorrowingDto() {
        return new ListOfBorrowingDto();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link LibraryServiceFault }
     * 
     */
    public LibraryServiceFault createLibraryServiceFault() {
        return new LibraryServiceFault();
    }

    /**
     * Create an instance of {@link ListOfObject }
     * 
     */
    public ListOfObject createListOfObject() {
        return new ListOfObject();
    }

    /**
     * Create an instance of {@link LibraryAddress }
     * 
     */
    public LibraryAddress createLibraryAddress() {
        return new LibraryAddress();
    }

    /**
     * Create an instance of {@link UserAccountDto }
     * 
     */
    public UserAccountDto createUserAccountDto() {
        return new UserAccountDto();
    }

    /**
     * Create an instance of {@link BookDto }
     * 
     */
    public BookDto createBookDto() {
        return new BookDto();
    }

    /**
     * Create an instance of {@link UserAddress }
     * 
     */
    public UserAddress createUserAddress() {
        return new UserAddress();
    }

    /**
     * Create an instance of {@link BookBooking }
     * 
     */
    public BookBooking createBookBooking() {
        return new BookBooking();
    }

    /**
     * Create an instance of {@link Library }
     * 
     */
    public Library createLibrary() {
        return new Library();
    }

    /**
     * Create an instance of {@link UserAccount }
     * 
     */
    public UserAccount createUserAccount() {
        return new UserAccount();
    }

    /**
     * Create an instance of {@link BookBookingDto }
     * 
     */
    public BookBookingDto createBookBookingDto() {
        return new BookBookingDto();
    }

    /**
     * Create an instance of {@link BorrowingDto }
     * 
     */
    public BorrowingDto createBorrowingDto() {
        return new BorrowingDto();
    }

    /**
     * Create an instance of {@link Borrowing }
     * 
     */
    public Borrowing createBorrowing() {
        return new Borrowing();
    }

    /**
     * Create an instance of {@link ListOfBookBookingDto }
     * 
     */
    public ListOfBookBookingDto createListOfBookBookingDto() {
        return new ListOfBookBookingDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LibraryServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://borrowing.service.exposure.libraryWS.philippe75.com/", name = "LibraryServiceException")
    public JAXBElement<LibraryServiceException> createLibraryServiceException(LibraryServiceException value) {
        return new JAXBElement<LibraryServiceException>(_LibraryServiceException_QNAME, LibraryServiceException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://borrowing.service.exposure.libraryWS.philippe75.com/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
