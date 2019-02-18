package com.philippe75.libraryBatch;


import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.sym.Name;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingDto;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.BorrowingServiceImplService;
import com.philippe75.libraryBatch.stub.generated.borrowingServ.LibraryServiceException_Exception;

@Component
public class ListEmail {
	
	@Scheduled(fixedRate = 5000)
	public List<String> getEmails(){
		List<String> listAdress = new ArrayList<>();
		BorrowingService borrowingService = new BorrowingServiceImplService().getBorrowingServiceImplPort();
		try {
			List<BorrowingDto> lb = (List<BorrowingDto>) borrowingService.getAllLateBorrowings().getItem();
			for (BorrowingDto bd : lb) {
				listAdress.add(bd.getUserAccount().getEmail());
			}
		
		} catch (LibraryServiceException_Exception e) {
			e.printStackTrace();
		}
		
		listAdress.forEach(e -> System.out.println(e));
		System.out.println("-------------------------");
		return listAdress;
	}
	
	
	
}
