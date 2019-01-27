package com.philippe75.libraryWS.consumer.contract.handler;

import com.philippe75.libraryWS.consumer.contract.dao.BookDao;
import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;


public interface DaoHandler {	
	UserAccountDao getUserAccountDao();
	BookDao getBookDao();
}
