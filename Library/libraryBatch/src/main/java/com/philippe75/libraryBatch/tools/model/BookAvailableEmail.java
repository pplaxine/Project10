package com.philippe75.libraryBatch.tools.model;

import java.io.Serializable;

import com.philippe75.libraryBatch.stub.generated.bookService.BookDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.UserAccount;

/**
 * <b>BookAvailableEmail model object</b>
 * 	  
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>a userAccount</li>
 * <li>a bookDto</li>
 * <li>a email content</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class BookAvailableEmail implements Serializable {
	
	/**
	 * Member receiver of the email 
	 */
	private UserAccount userAccount;
	/**
	 * The book mentionned in the email
	 */
	private BookDto bookDto;
	/**
	 * The content of the email 
	 */
	private String emailContent;
	
	public BookAvailableEmail() {};
	
	public BookAvailableEmail(UserAccount userAccount, BookDto bookDto) {
		this.userAccount = userAccount;
		this.bookDto = bookDto;
	};
	
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public BookDto getBookDto() {
		return bookDto;
	}
	public void setBookDto(BookDto bookDto) {
		this.bookDto = bookDto;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	} 
	
	
}
