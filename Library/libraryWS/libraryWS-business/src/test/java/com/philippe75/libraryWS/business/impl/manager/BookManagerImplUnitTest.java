package com.philippe75.libraryWS.business.impl.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.philippe75.libraryWS.business.dto.BookDto;
import com.philippe75.libraryWS.business.impl.handler.ManagerHandlerImpl;
import com.philippe75.libraryWS.consumer.impl.dao.BookDaoImpl;
import com.philippe75.libraryWS.consumer.impl.handler.DaoHandlerImpl;
import com.philippe75.libraryWS.model.book.Book;
import com.philippe75.libraryWS.model.book.Genre;
import com.philippe75.libraryWS.model.library.Library;

@RunWith(MockitoJUnitRunner.class)
public class BookManagerImplUnitTest {
	
	@InjectMocks
	private ManagerHandlerImpl managerHandler;
	@InjectMocks
	private BookManagerImpl bookManager;
	@InjectMocks
	private DaoHandlerImpl daoHandler;
	@Mock
	private BookDaoImpl bookDao;
	
    private Book book;
    private BookDto bookDto;
    
    @Before
    public void executeBeforeEach() {
    	//set the mock in abstract class
    	AbstractManager.configure(daoHandler);
    	//set the Mock in ManagerHandler class
    	ManagerHandlerImpl.configure(bookManager);
    	
    	
    	
		//STUB book
		book = new Book();
		book.setId(1);
		book.setAuthor("George Orwell");
		book.setName("1984");
		book.setGenre(Genre.SCIENCE_FICTION);
		book.setSummary("Winston Smith is a low-ranking member of the ruling Party in London, "
				+ "in the nation of Oceania. Everywhere Winston goes, even his own home, the "
				+ "Party watches him through telescreens; everywhere he looks he sees the face "
				+ "of the Party’s seemingly omniscient leader, a figure ...");
		
		//-- Library --
		Library library = new Library();
    	library.setName("Pop");
		
		book.setLibrary(library);
		book.setAvailable(true); 
		
		//STUB bookDto
		bookDto = new BookDto();
		bookDto.setId(1);
		bookDto.setAuthor("George Orwell");
		bookDto.setName("1984");
		bookDto.setGenre(Genre.SCIENCE_FICTION);
		bookDto.setSummary("Winston Smith is a low-ranking member of the ruling Party in London, "
				+ "in the nation of Oceania. Everywhere Winston goes, even his own home, the "
				+ "Party watches him through telescreens; everywhere he looks he sees the face "
				+ "of the Party’s seemingly omniscient leader, a figure ...");
		bookDto.setAvailable(true);

    }
	
    //BookDto created from Book contains all the values 
	@Test
	public void bookModelToDtoTest() {
		
		BookDto bookDto = bookManager.bookModelToDto(book);

		assertEquals("bookModelToDto : Id ",bookDto.getId(), book.getId());
		assertEquals("bookModelToDto : Author ",bookDto.getAuthor(), book.getAuthor());
		assertEquals("bookModelToDto : Name ",bookDto.getName(), book.getName());
		assertEquals("bookModelToDto : Summary ",bookDto.getSummary(), book.getSummary());
		assertEquals("bookModelToDto : Genre ",bookDto.getGenre(), book.getGenre());
		assertEquals("bookModelToDto : Library ",bookDto.getLibrary(), book.getLibrary().getName());
		assertEquals("bookModelToDto : available ",bookDto.isAvailable(), book.isAvailable());
	}
	
    //Book created from BookDto contains all the values 
	@Test
	public void bookDtoToModelTest() {
		
		Book book = bookManager.bookDtoToModel(bookDto);

		assertEquals("bookDtoToModel : Id ",bookDto.getId(), book.getId());
		assertEquals("bookDtoToModel : Author ",bookDto.getAuthor(), book.getAuthor());
		assertEquals("bookDtoToModel : Name ",bookDto.getName(), book.getName());
		assertEquals("bookDtoToModel : Summary ",bookDto.getSummary(), book.getSummary());
		assertEquals("bookDtoToModel : Genre ",bookDto.getGenre(), book.getGenre());
		assertEquals("bookDtoToModel : Genre ",bookDto.getGenre(), book.getGenre());
		assertEquals("bookDtoToModel : available ",bookDto.isAvailable(), book.isAvailable());
		
	}
	
	//List of BookDto is created
	@Test
	public void getListBookByNameTest() throws Exception {

		String bookName = book.getName();
		//---- mock setup 
		List<Book> lb = new ArrayList<>();
		lb.add(book);
		lb.add(book);
		
		when(daoHandler.getBookDao().getListBookByName(bookName)).thenReturn(lb);
		//---------------
		
		List<BookDto> lbd = managerHandler.getBookManager().getListBookByName(bookName);
		System.out.println(lbd.size());
		assertTrue("The List of BookDto is bigger than expected",lbd.size() == 2);
		assertEquals("The list of BookDto don't contains the expected BookDto ", lbd.get(0).getName(), book.getName());
	}
	
	//List of BookDto is created
	@Test
	public void getListBookStartingByTest() throws Exception {

		String Letter = "r";
		//---- mock setup 
		List<Book> lb = new ArrayList<>();
		lb.add(book);
		lb.add(book);
		
		when(daoHandler.getBookDao().getListBookStartingBy(Letter)).thenReturn(lb);
		//---------------
		
		List<BookDto> lbd = managerHandler.getBookManager().getListBookStartingBy(Letter);
		assertTrue("The List of BookDto is bigger than expected",lbd.size() == 2);
		assertEquals("The list of BookDto don't contains the expected BookDto ", lbd.get(0).getName(), book.getName());
		
	}
	

}
