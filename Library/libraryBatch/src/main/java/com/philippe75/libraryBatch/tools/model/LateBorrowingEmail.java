package com.philippe75.libraryBatch.tools.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.philippe75.libraryBatch.stub.generated.borrowingServ.UserAccount;


public class LateBorrowingEmail implements Serializable{
	
	private UserAccount userAccount;
	private List<Borrowing> listBorrowing = new ArrayList<>();
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
