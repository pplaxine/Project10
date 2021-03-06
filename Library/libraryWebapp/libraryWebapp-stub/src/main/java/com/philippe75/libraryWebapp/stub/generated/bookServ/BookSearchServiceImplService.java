
package com.philippe75.libraryWebapp.stub.generated.bookServ;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BookSearchServiceImplService", targetNamespace = "http://booksearch.service.exposure.libraryWS.philippe75.com/", wsdlLocation = "http://localhost:8080/libraryWS-webservice/libraryService?wsdl")
public class BookSearchServiceImplService
    extends Service
{

    private final static URL BOOKSEARCHSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException BOOKSEARCHSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName BOOKSEARCHSERVICEIMPLSERVICE_QNAME = new QName("http://booksearch.service.exposure.libraryWS.philippe75.com/", "BookSearchServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/libraryWS-webservice/libraryService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BOOKSEARCHSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        BOOKSEARCHSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public BookSearchServiceImplService() {
        super(__getWsdlLocation(), BOOKSEARCHSERVICEIMPLSERVICE_QNAME);
    }

    public BookSearchServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), BOOKSEARCHSERVICEIMPLSERVICE_QNAME, features);
    }

    public BookSearchServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, BOOKSEARCHSERVICEIMPLSERVICE_QNAME);
    }

    public BookSearchServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BOOKSEARCHSERVICEIMPLSERVICE_QNAME, features);
    }

    public BookSearchServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BookSearchServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BookSearchService
     */
    @WebEndpoint(name = "BookSearchServiceImplPort")
    public BookSearchService getBookSearchServiceImplPort() {
        return super.getPort(new QName("http://booksearch.service.exposure.libraryWS.philippe75.com/", "BookSearchServiceImplPort"), BookSearchService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BookSearchService
     */
    @WebEndpoint(name = "BookSearchServiceImplPort")
    public BookSearchService getBookSearchServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://booksearch.service.exposure.libraryWS.philippe75.com/", "BookSearchServiceImplPort"), BookSearchService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BOOKSEARCHSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw BOOKSEARCHSERVICEIMPLSERVICE_EXCEPTION;
        }
        return BOOKSEARCHSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
