package com.philippe75.libraryWS.model.book;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.philippe75.libraryWS.model.library.Library;

/**
 * <b>Model object of a Book.</b>
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
 * <li>a list of Borrowings</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Entity
public class Book {
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
	private Library library;
	/**
	 * Genre of the book.
	 */
	private Genre genre;
	/**
	 * The historic of the borrowings attached to the book.
	 */
	private Collection<Borrowing> listBorrowing = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@ManyToOne
	@JoinColumn(name="library_id")
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="book_genre")
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	@OneToMany(mappedBy="book")
	public Collection<Borrowing> getListBorrowing() {
		return listBorrowing;
	}
	public void setListBorrowing(Collection<Borrowing> listBorrowing) {
		this.listBorrowing = listBorrowing;
	}
	

	
	
	
	
}
