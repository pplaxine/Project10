package com.philippe75.libraryWS.business.contract.handler;

import com.philippe75.libraryWS.business.contract.manager.BookManager;
import com.philippe75.libraryWS.business.contract.manager.UserAccountManager;

public interface ManagerHandler {
	UserAccountManager getUserAccountManager();
	BookManager getBookManager();
}
