package com.philippe75.libraryWS.model.book;


public enum BookGenre {
	
	TRAGEDY("Tragedy"),
	SCIENCE_FICTION("Science fiction"),
	FANTASY("Fantasy");

	private String bookGenre = "";
	
	BookGenre(String bookGenre){
		this.bookGenre=bookGenre;
		
	}

	public String getBookGenre() {
		return bookGenre;
	}
	
	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	@Override
	public String toString() {
		return bookGenre;
	}
}
