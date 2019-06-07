
package com.philippe75.libraryBatch.stub.generated.bookService;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "LibraryServiceException", targetNamespace = "http://booksearch.service.exposure.libraryWS.philippe75.com/")
public class LibraryServiceException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private LibraryServiceException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public LibraryServiceException_Exception(String message, LibraryServiceException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public LibraryServiceException_Exception(String message, LibraryServiceException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.philippe75.libraryBatch.stub.generated.bookService.LibraryServiceException
     */
    public LibraryServiceException getFaultInfo() {
        return faultInfo;
    }

}
