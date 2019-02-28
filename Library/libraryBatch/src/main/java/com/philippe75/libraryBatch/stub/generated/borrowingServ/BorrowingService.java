
package com.philippe75.libraryBatch.stub.generated.borrowingServ;

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
@WebService(name = "BorrowingService", targetNamespace = "http://borrowing.service.exposure.libraryWS.philippe75.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BorrowingService {


    /**
     * 
     * @return
     *     returns com.philippe75.libraryBatch.stub.generated.borrowingServ.ListOfBorrowingDto
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getAllLateBorrowingsRequest", output = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getAllLateBorrowingsResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getAllLateBorrowings/Fault/LibraryServiceException")
    })
    public ListOfBorrowingDto getAllLateBorrowings()
        throws LibraryServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns com.philippe75.libraryBatch.stub.generated.borrowingServ.ListOfBorrowingDto
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getAllBorrowingForUserRequest", output = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getAllBorrowingForUserResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getAllBorrowingForUser/Fault/LibraryServiceException")
    })
    public ListOfBorrowingDto getAllBorrowingForUser(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws LibraryServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @Action(input = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/endBorrowingRequest", output = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/endBorrowingResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/endBorrowing/Fault/LibraryServiceException")
    })
    public void endBorrowing(
        @WebParam(name = "arg0", partName = "arg0")
        BorrowingDto arg0)
        throws LibraryServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @Action(input = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/extendBorrowingRequest", output = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/extendBorrowingResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/extendBorrowing/Fault/LibraryServiceException")
    })
    public void extendBorrowing(
        @WebParam(name = "arg0", partName = "arg0")
        BorrowingDto arg0)
        throws LibraryServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getBorrowingByIdRequest", output = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getBorrowingByIdResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/getBorrowingById/Fault/LibraryServiceException")
    })
    public BorrowingDto getBorrowingById(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0)
        throws LibraryServiceException_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws LibraryServiceException_Exception
     */
    @WebMethod
    @Action(input = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/createBorrowingRequest", output = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/createBorrowingResponse", fault = {
        @FaultAction(className = LibraryServiceException_Exception.class, value = "http://borrowing.service.exposure.libraryWS.philippe75.com/BorrowingService/createBorrowing/Fault/LibraryServiceException")
    })
    public void createBorrowing(
        @WebParam(name = "arg0", partName = "arg0")
        BorrowingDto arg0)
        throws LibraryServiceException_Exception
    ;

}
