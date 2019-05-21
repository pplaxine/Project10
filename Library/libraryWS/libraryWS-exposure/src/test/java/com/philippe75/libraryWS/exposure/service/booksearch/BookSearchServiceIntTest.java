package com.philippe75.libraryWS.exposure.service.booksearch;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.exposure.bootstrap.SpringConfiguration;
import com.philippe75.libraryWS.model.exception.saop.LibraryServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class BookSearchServiceIntTest {
	
	
	@Inject
	private ManagerHandler managerHandler;
	
	//There are 2 different books with name starting by "l"
	@Test
	public void getListBookStartingByIntTest() throws LibraryServiceException {

		List<BookDto> lbd = managerHandler.getBookManager().getListBookStartingBy("l");
		assertTrue("Result different from expected 2", lbd.size() == 2);
		
	}
	
	//there is 1 book with name "Legend" 
	@Test
	public void getListBookByNameIntTest() throws Exception {
		List<BookDto> lbd = managerHandler.getBookManager().getListBookByName("Legend");
		assertTrue("Result different from expected 1", lbd.size() == 1);
		
	}
	
}
