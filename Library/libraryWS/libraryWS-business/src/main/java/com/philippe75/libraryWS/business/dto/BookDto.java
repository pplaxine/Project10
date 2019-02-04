package com.philippe75.libraryWS.business.dto;

import com.philippe75.libraryWS.model.book.Genre;

/**
 * <b>Dto object of a Book.</b>
 * <p>
 * 	Used to transport only the necessary informations of a Book object.   
 * </p>
 * 
 * <p>
 * Characterized by : 
 * </p>
 * <ul>
 * <li>an id</li>
 * <li>a name</li>
 * <li>an author</li>
 * <li>a summary</li>
 * <li>the availability</li>
 * <li>a library</li>
 * <li>a book genre</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class BookDto {
	
	/**
	 * Unique id of the book.
	 */
	private int id;
	/**
	 * Name of the book.
	 */
	private String name;
	/**
	 * Author of the book.
	 */
	private String author;
	/**
	 * Summary of the book (the back page).
	 */
	private String summary;
	/**
	 * Is the book already borrowed by a member.
	 */
	private boolean available;
	/**
	 * Library to which the book belongs to (The city has several libraries).
	 */
	private String library;
	/**
	 * Genre of the book.
	 */
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
