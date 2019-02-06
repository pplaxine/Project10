package com.philippe75.libraryWebapp.business.impl.manager;

import java.util.List;

import javax.inject.Named;

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

}
