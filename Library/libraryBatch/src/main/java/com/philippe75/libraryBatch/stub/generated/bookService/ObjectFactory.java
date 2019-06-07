
package com.philippe75.libraryBatch.stub.generated.bookService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.philippe75.libraryBatch.stub.generated.bookService package. 
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

    private final static QName _LibraryServiceException_QNAME = new QName("http://booksearch.service.exposure.libraryWS.philippe75.com/", "LibraryServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.philippe75.libraryBatch.stub.generated.bookService
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
     * Create an instance of {@link ListOfBookDto }
     * 
     */
    public ListOfBookDto createListOfBookDto() {
        return new ListOfBookDto();
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
     * Create an instance of {@link BookDto }
     * 
     */
    public BookDto createBookDto() {
        return new BookDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LibraryServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://booksearch.service.exposure.libraryWS.philippe75.com/", name = "LibraryServiceException")
    public JAXBElement<LibraryServiceException> createLibraryServiceException(LibraryServiceException value) {
        return new JAXBElement<LibraryServiceException>(_LibraryServiceException_QNAME, LibraryServiceException.class, null, value);
    }

}
