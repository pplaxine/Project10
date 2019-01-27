package com.philippe75.libraryWS.consumer.contract.dao;

import java.util.List;

import com.philippe75.libraryWS.model.book.Book;

public interface BookDao {
	
	List<Book> getListBookByName(String name);
	
}
