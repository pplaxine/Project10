package com.philippe75.libraryWebapp.webapp.action;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;


public class BorrowingAction extends ActionSupport {
	
	@Inject
	ManagerHandler managerHandler;
	
	//income 

	
	//outcome
	private List<BorrowingDto> listBorrowingForUser;

	//G&S
	public List<BorrowingDto> getListBorrowingForUser() {
		return listBorrowingForUser;
	}

	
	//METHODS
	public String doListBorrowingForUser() {
		
		try {
			listBorrowingForUser = managerHandler.getBorrowingDtoManager().getAllBorrowingForUser("JTille");
		} catch (LibraryServiceException_Exception e) {
			if((e.getMessage()).equals("NoResultException")) {
				this.addActionError(getText("Aucune reservation existante."));
			}else {
				this.addActionError(getText("Une erreur inatendue est survenue. Veuillez re-essayer plus tard."));
			}
		}
		
		return ActionSupport.SUCCESS; 
	}
	
}
