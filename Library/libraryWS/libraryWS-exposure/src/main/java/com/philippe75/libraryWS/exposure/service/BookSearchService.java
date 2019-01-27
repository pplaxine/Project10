package com.philippe75.libraryWS.exposure.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.philippe75.libraryWS.business.dto.BookDto;

@WebService
@SOAPBinding(style = Style.RPC)
public interface BookSearchService {

	@WebMethod
	public List<BookDto >getListBookByName(String name);
	
	@WebMethod
	public String test();
}
