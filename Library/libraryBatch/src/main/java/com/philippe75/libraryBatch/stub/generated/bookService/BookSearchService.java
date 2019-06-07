
package com.philippe75.libraryBatch.stub.generated.bookService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BookSearchService", targetNamespace = "http://booksearch.service.exposure.libraryWS.philippe75.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BookSearchService {


    /**
     * 
     * @param arg0
     * @return
     *     returns com.philippe75.libraryBatch.stub.generated.bookService.ListOfBookDto
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://booksearch.service.exposure.libraryWS.philippe75.com/BookSearchService/getListBookStartingByRequest", output = "http://booksearch.service.exposure.libraryWS.philippe75.com/BookSearchService/getListBookStartingByResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://booksearch.service.exposure.libraryWS.philippe75.com/BookSearchService/getListBookStartingBy/Fault/LibraryServiceException")
    })
    public ListOfBookDto getListBookStartingBy(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws LibraryServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns com.philippe75.libraryBatch.stub.generated.bookService.ListOfBookDto
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://booksearch.service.exposure.libraryWS.philippe75.com/BookSearchService/getListBookByNameRequest", output = "http://booksearch.service.exposure.libraryWS.philippe75.com/BookSearchService/getListBookByNameResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://booksearch.service.exposure.libraryWS.philippe75.com/BookSearchService/getListBookByName/Fault/LibraryServiceException")
    })
    public ListOfBookDto getListBookByName(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws LibraryServiceException_Exception
    ;

}
