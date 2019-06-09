package com.philippe75.libraryBatch.tools.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.UserAccount;

/**
 * <b>LateBorrowingEmail model object</b>
 * 	  
 * <p>
 * Characterized by : 
 * <p>
 * <ul>
 * <li>a userAccount</li>
 * <li>a list of borrowings</li>
 * <li>a email</li>
 * </ul>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public class LateBorrowingEmail implements Serializable{
	/**
	 * Member that made the borrowing
	 */
	private UserAccount userAccount;
	/**
	 * A list of the late borrowings
	 */
	private List<Borrowing> listBorrowing = new ArrayList<>();
	/**
	 * The content of the email
	 */
	private String emailContent;
	
	//G&S
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public List<Borrowing> getListBorrowing() {
		return listBorrowing;
	}
	public void setListBorrowing(List<Borrowing> listBorrowing) {
		this.listBorrowing = listBorrowing;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	
	
	
	
	
}
