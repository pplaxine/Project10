package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.philippe75.libraryWebapp.business.contract.manager.BookDtoManager;
import com.philippe75.libraryWebapp.stub.generated.bookServ.BookDto;
import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:bootstrapContext.xml"})
public class BookDtoManagerImplIntTest {
	
	@Inject
	BookDtoManager bookDtoManager;
	
	//There are 2 different books with name starting by "l"
	@Test
	public void getListBookStartingByIntTest() throws LibraryServiceException_Exception{

		List<BookDto> lbd = bookDtoManager.getListBookStartingBy("l");
		assertTrue("Result different from expected 2", lbd.size() == 2);
		
	}
	
	//there is 1 book with name "Legend" 
	@Test
	public void getListBookByNameIntTest() throws Exception {
		List<BookDto> lbd = bookDtoManager.getListBookByName("Legend");
		assertTrue("Result different from expected 1", lbd.size() == 1);
		
	}
	
}
