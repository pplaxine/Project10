package com.philippe75.libraryWS.business.dto;

import java.util.ArrayList;
import java.util.Collection;

import com.philippe75.libraryWS.model.book.Borrowing;
import com.philippe75.libraryWS.model.book.Genre;
import com.philippe75.libraryWS.model.library.Library;

public class BookDto {
	
	private int id;
	private String name;
	private String author;
	private String summary;
	private boolean available;
	private String library;
	private Genre genre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getLibrary() {
		return library;
	}
	public void setLibrary(String library) {
		this.library = library;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	
}
