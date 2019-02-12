package com.philippe75.libraryWebapp.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.Borrowing;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;


public class BorrowingAction extends ActionSupport implements SessionAware{
	
	@Inject
	ManagerHandler managerHandler;
	
	private Map<String, Object> session;
	
	//income 
	private Integer bookId;
	private String borrowingId;
	//outcome
	private List<BorrowingDto> listBorrowingForUser;
	private String numberOfWeekExtend;
 
	//G&S
	public List<BorrowingDto> getListBorrowingForUser() {
		return listBorrowingForUser;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getNumberOfWeekExtend() {
		return numberOfWeekExtend;
	}
	public void setNumberOfWeekExtend(String numberOfWeekExtend) {
		this.numberOfWeekExtend = numberOfWeekExtend;
	}
	public String getBorrowingId() {
		return borrowingId;
	}
	public void setBorrowingId(String borrowingId) {
		this.borrowingId = borrowingId;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	//METHODS
	public String doListBorrowingForUser() {
		
		try {
			UserAccountDto uad = (UserAccountDto)this.session.get("user");
			listBorrowingForUser = managerHandler.getBorrowingDtoManager().getAllBorrowingForUser(uad.getUserMemberId());
		} catch (LibraryServiceException_Exception e) {
			if((e.getMessage()).equals("NoResultException")) {
				this.addActionError(getText("Aucune reservation existante."));
			}else {
				this.addActionError(getText("Une erreur inatendue est survenue. Veuillez re-essayer plus tard."));
			}
		}
		return ActionSupport.SUCCESS; 
	}
	
	public String extendBorrowing() {
		if(!StringUtils.isAllEmpty(borrowingId, numberOfWeekExtend)) {
			
			try {
				managerHandler.getBorrowingDtoManager().extendBorrowing(Integer.valueOf(borrowingId), Integer.valueOf(numberOfWeekExtend));
				addActionMessage("Prolongement de la location effectuée avec succès");
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (LibraryServiceException_Exception e) {
				if((e.getMessage()).equals("NoResultException")) {
					this.addActionError(getText("Aucune reservation existante."));
				}else if((e.getMessage()).equals("AlreadyExtendedException")){
					this.addActionError(getText("Vous avez déjà effectué un renouvellement pour ce livre"));
				}else {
					this.addActionError(getText("Une erreur inatendue est survenue. Veuillez re-essayer plus tard."));
				}
			}
		}
		return ActionSupport.SUCCESS;
	}


}
