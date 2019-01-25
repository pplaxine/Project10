package com.philippe75.libraryWS.model.book;


public enum Genre {
	
	TRAGEDY("Tragedy"),
	SCIENCE_FICTION("Science fiction"),
	FANTASY("Fantasy");

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
