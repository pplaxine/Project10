package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.philippe75.libraryWebapp.stub.generated.bookServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookDto;

@RunWith(MockitoJUnitRunner.class)
public class BookDtoManagerImplUnitTest extends AbstractManagerServiceAccess {

	@InjectMocks
	BookDtoManagerImpl bookDtoManager;
	
	private String bookNameWithAuthor;
	private List<BookDto> lbd;
	private BookDto bookDto;
	@Before
	public void executeBeforeAll() throws ParseException {
		lbd = new ArrayList<>();
		bookNameWithAuthor = "Roméo et Juliette - William Shakespeare";
		
		//BookDto Stub
		bookDto = new BookDto();
		bookDto.setName("1984");
		bookDto.setAuthor("George Orwell");
		
		//List BookDto stub 
		lbd = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			if(i%2 == 0 ) {
				bookDto.setAvailable(false);
			}else {
				bookDto.setAvailable(true);
			}
			lbd.add(bookDto);
		}
	}
	
	@Test
	public void getBookNameOnlyUnitTest() {
		
		String bookNameOnly = BookDtoManagerImpl.getBookNameOnly(bookNameWithAuthor);
		
		assertTrue(bookNameOnly.equals("Roméo et Juliette"));
	}
	
	// Only the name of the book is kept 
	@Test
	public void getAuthorNameOnlyUnitTest() {
		String bookNameOnly = BookDtoManagerImpl.getBookAuthorOnly(bookNameWithAuthor);
		
		assertTrue(bookNameOnly.equals("William Shakespeare"));
	}

 
}
