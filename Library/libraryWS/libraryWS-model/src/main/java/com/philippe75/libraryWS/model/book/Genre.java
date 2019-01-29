package com.philippe75.libraryWS.model.book;


/**
 * <b>Enumeration of books Genre.</b>
 * 
 * <p>
 * Characterized by the most common literary genre. 
 * <p>
 * 
 * @see Book#getGenre()
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public enum Genre {
	
	TRAGEDY("Tragedy"),
	SCIENCE_FICTION("Science fiction"),
	FANTASY("Fantasy"),
	MYTHOLOGY("Mythology"),
	ADVENTURE("Adventure"),
	MYSTERY("Mystery"),
	DRAMA("Drama"),
	ROMANCE("Romance"),
	SATIRE("Satire"),
	HORROR("Horror"),
	TRAGIC_COMEDY("Tragic comedy"),
	YOUNG_ADULT_FICTION("Young adult fiction"),
	DYSTOPIA("Dystopia"),
	ACTION_THRILLER("Action thriller");

	private String genre = "";
	
	Genre(String genre){
		this.genre=genre;
		
	}

	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return genre;
	}
}
