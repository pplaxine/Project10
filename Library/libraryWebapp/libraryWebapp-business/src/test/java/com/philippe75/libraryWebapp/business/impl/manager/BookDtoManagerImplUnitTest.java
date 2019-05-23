package com.philippe75.libraryWebapp.business.impl.manager;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookDtoManagerImplUnitTest {

	@InjectMocks
	BookDtoManagerImpl bookDtoManager;
	
	@Test
	public void getBookNameOnlyUnitTest() {
		String bookNameWithAuthor = "Roméo et Juliette - William Shakespeare";
		String bookNameOnly = bookDtoManager.getBookNameOnly(bookNameWithAuthor);
		
		assertTrue(bookNameOnly.equals("Roméo et Juliette"));
	}

 
}