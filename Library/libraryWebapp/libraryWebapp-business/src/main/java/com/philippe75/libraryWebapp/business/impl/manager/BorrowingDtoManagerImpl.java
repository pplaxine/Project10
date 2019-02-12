package com.philippe75.libraryWebapp.business.impl.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.philippe75.libraryWebapp.business.contract.manager.BorrowingDtoManager;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.LibraryServiceException_Exception;
import com.philippe75.libraryWebapp.stub.generated.borrowingServ.BorrowingDto;

/**
 * <b>Implements BorrowingManager Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("borrowingDtoManager")
public class BorrowingDtoManagerImpl extends AbstractManagerServiceAccess implements BorrowingDtoManager{

	/**
	 * Get all the {@link BorrowingDto} of a user.
	 * 
	 * @param userMemberID the user member id of the user
	 * @return List<BorrowingDto> list of Dto object of {@link Borrowing} of a user.
	 */
	@Override
	public List<BorrowingDto> getAllBorrowingForUser(String userMemberId) throws LibraryServiceException_Exception {
		return getBorrowingService().getAllBorrowingForUser(userMemberId).getItem();
	}

	@Override
	public void extendBorrowing(Integer borrowingId, Integer numberOfWeek) throws LibraryServiceException_Exception {
		
		BorrowingDto bd = getBorrowingService().getBorrowingById(borrowingId); 

		Date supposedEndDate = bd.getSupposedEndDate().toGregorianCalendar().getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(supposedEndDate);
		cal.add(Calendar.WEEK_OF_YEAR, numberOfWeek);
		Date secondSupposedToEndDate = cal.getTime();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(secondSupposedToEndDate);
		try {
			XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			bd.setSecondSupposedEndDate(xgc);
			getBorrowingService().extendBorrowing(bd);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

}
