package com.philippe75.libraryWebapp.webapp.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.libraryWebapp.business.contract.handler.ManagerHandler;

import com.philippe75.libraryWebapp.stub.generated.authServ.UserAccountDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BookBookingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;


public class BorrowingAction extends ActionSupport implements SessionAware{
	
	@Inject
	ManagerHandler managerHandler;
	
	private Map<String, Object> session;
	
	//income 
	private Integer bookId;
	private String borrowingId;
	private Integer bookingId;
	//outcome
	private List<BorrowingDto> listBorrowingForUser;
	private String numberOfWeekExtend;
	private List<BookBookingDto> listBookBookingForUser;
	private Map<BookBookingDto, BorrowingDto> listNextEndingBorrowingForBookbooking;
	private Map<BookBookingDto, Integer> listPositionOfMemberInReservationListForBookbooking;
	
	//G&S
	public List<BorrowingDto> getListBorrowingForUser() {
		return listBorrowingForUser;
	}
	public List<BookBookingDto> getListBookBookingForUser() {
		return listBookBookingForUser;
	}
	public Map<BookBookingDto, BorrowingDto> getListNextEndingBorrowingForBookbooking() {
		return listNextEndingBorrowingForBookbooking;
	}
	public Map<BookBookingDto, Integer> getListPositionOfMemberInReservationListForBookbooking() {
		return listPositionOfMemberInReservationListForBookbooking;
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
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	//METHODS
	public String doListBorrowingForUser() {
		try {
			//cancel booking 
			if(bookingId != null) {
				managerHandler.getBorrowingDtoManager().endBooking(bookingId);
				bookingId = null;
			}

			//get user member id
			UserAccountDto uad = (UserAccountDto)this.session.get("user");
			
			//get all borrowings for user
			listBorrowingForUser = managerHandler.getBorrowingDtoManager().getAllBorrowingForUser(uad.getUserMemberId());
			
			//get all active bookings for user 
			listBookBookingForUser = managerHandler.getBorrowingDtoManager().getAllNotEndedBookingsForMember(uad.getUserMemberId());
			
			//get map of bookings and nextEndingBorrowing
			listNextEndingBorrowingForBookbooking = managerHandler.getBorrowingDtoManager().getNexBorrowingToComeToEndForEachBookBooking(listBookBookingForUser);
			
			//get map bookings and number of people in front of member in the waiting list 
			listPositionOfMemberInReservationListForBookbooking = managerHandler.getBorrowingDtoManager().getPositionInWaintingListForEachBookBooking(listBookBookingForUser);
			
			
		} catch (LibraryServiceException_Exception e) {
			if((e.getMessage()).equals("NoResultException")) {
				this.addActionError(getText("Aucune reservation existante."));
			}else {
				this.addActionError(getText("Une erreur inatendue est survenue. Veuillez re-essayer plus tard."));
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ActionSupport.ERROR; 
		}
		return ActionSupport.SUCCESS; 
	}
	
	public String extendBorrowing() {
		
		String result = ActionSupport.ERROR;
		if(!StringUtils.isAllEmpty(borrowingId, numberOfWeekExtend)) {
			
			try {
				managerHandler.getBorrowingDtoManager().extendBorrowing(Integer.valueOf(borrowingId), Integer.valueOf(numberOfWeekExtend));
				addActionMessage("Prolongement de la location de votre livre effectuée avec succès");
				result = ActionSupport.SUCCESS;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (LibraryServiceException_Exception ex) {
				ex.getStackTrace();
				if((ex.getMessage()).equals("NoResultException")) {
					this.addActionError(getText("Aucune reservation existante."));
				}else if((ex.getMessage()).equals("AlreadyExtendedException")){
					this.addActionError(getText("Vous avez déjà effectué un renouvellement pour ce livre"));
				}else {
					this.addActionError(getText("Une erreur inatendue est survenue. Veuillez re-essayer plus tard."));
				}
			}
		}
		return result;
	}

}
