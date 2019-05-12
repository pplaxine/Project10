package com.philippe75.libraryWS.exposure.service.booksearch;

import javax.inject.Inject;

import org.h2.tools.RunScript;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.exposure.bootstrap.SpringConfiguration;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.Genre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class BookSearchServiceIntTest {
	
	
	@Inject
	private ManagerHandler managerHandler;
	
	@Test
	public void getListBookStartingBy() throws Exception {
		
		for (BookDto book :  managerHandler.getBookManager().getListBookStartingBy("l")) {
			System.out.println(book.getName());
		}
		
		int size = managerHandler.getBookManager().getListBookByName("Legend").size();
		System.out.println(size);
		
		BorrowingDto borrowing2 = managerHandler.getBorrowingManager().getBorrowingById(2);
		
		
		managerHandler.getBorrowingManager().endBorrowing(borrowing2);
		
		
	}
}
