package com.philippe75.libraryWS.exposure.service;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BookDto;

@WebService(endpointInterface="com.philippe75.libraryWS.exposure.service.BookSearchService")
public class BookSearchServiceImpl extends SpringBeanAutowiringSupport implements BookSearchService{

	@Inject
	private ManagerHandler managerHandler;
	
	@Override
	public List<BookDto> getListBookByName(String name) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		List<BookDto> list = managerHandler.getBookManager().getListBookByName(name);
		System.out.println(list.size());
		return list;
	}

	@Override
	public String test() {
		return "Houlalala !";
	}
	
	

}
