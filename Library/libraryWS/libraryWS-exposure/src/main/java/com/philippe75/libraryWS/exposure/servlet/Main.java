package com.philippe75.libraryWS.exposure.servlet;


import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.libraryWS.business.contract.handler.ManagerHandler;
import com.philippe75.libraryWS.business.dto.BorrowingDto;
import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;

public class Main extends HttpServlet {

	public static final String VUE_MAIN ="/WEB-INF/main.jsp";
	
	@Inject
	private ManagerHandler managerHandler; 
	
	@Inject
	private DaoHandler daoHandler;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
//		try {
//			UserAccountDto uad = managerHandler.getUserAccountManager().getUserAccountByMemberId("JTill", "de");
//			System.out.println("Cette persone existe !");
//		
//		} catch (AuthentificationException e) {
//			System.out.println( " Depuis methode main" + e.getMessage());
//		}
//		
		
		
//			try {
//				UserAccountDto uad = managerHandler.getUserAccountManager().getUserAccountByMemberId("JTille");
//				System.out.println(uad.getFirstName());
//			} catch (AuthentificationException e) {
//				System.out.println(e.getMessage());
//			}
			
		

//			try {
//				UserAccountDto ua = managerHandler.getUserAccountManager().saveUserAccountPw("MSegaux", "Bloom");
//			} catch (AuthentificationException e) {
//				e.getMessage();
//			}
			
		
//			try {
//				List<BookDto> listbook = managerHandler.getBookManager().getListBookStartingBy("A");
//				listbook.forEach(e -> System.out.println(e.getName()));
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		
//		try {
//			List<BorrowingDto> listBorrowing = managerHandler.getBorrowingManager().getAllBorrowingForUser("MSegaux");
//			for (BorrowingDto borrowing : listBorrowing) {
//			//	System.out.println("Firstname " + borrowing.getUserAccount().getFirstName());
//			//	System.out.println("Name + start date " + borrowing.getBook().getName() + " " + borrowing.getStartDate());
//			//	System.out.println("Le password" + borrowing.getUserAccount().getPassword());
//				System.out.println(borrowing.getStartDate());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		BorrowingDto bd = new BorrowingDto();
//		bd.setExtended(false);
//		bd.setSecondSupposedEndDate(new Date());
//		bd.setId(3);
//		
//			try {
//				managerHandler.getBorrowingManager().extendBorrowing(bd);
//			} catch (LibraryServiceException e) {
//				e.getMessage();
//			}
//		
		
//		BorrowingDto borDto = new BorrowingDto();
//		try {
//			
//			borDto.setStartDate(new Date());
//			borDto.setSupposedEndDate(new Date());
//			
//			Book book = new Book();
//			book.setId(9);
//			borDto.setBook(book);
//			
//			UserAccount ua = new UserAccount();
//			ua.setUserMemberId("JTille");
//			borDto.setUserAccount(ua);
//			
//			managerHandler.getBorrowingManager().createBorrowing(borDto);
//			
//		} catch (Exception e) {
//		
//			e.printStackTrace();
//		}
//	
		
//		BorrowingDto borrowingDto = new BorrowingDto();
//		borrowingDto.setId(4);
//		borrowingDto.setEffectiveEndDate(new Date());
//		
//		try {
//			managerHandler.getBorrowingManager().endBorrowing(borrowingDto);
//		} catch (LibraryServiceException e) {
//			e.printStackTrace();
//		}
		
		try {
			List<BorrowingDto> lb =  managerHandler.getBorrowingManager().getAllLateBorrowings();
			lb.forEach(e -> System.out.println(e.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
