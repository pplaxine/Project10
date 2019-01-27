package com.philippe75.libraryWS.consumer.impl.dao;

import javax.inject.Named;

import org.hibernate.Session;
import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;
import com.philippe75.libraryWS.model.user.UserAccount;

@Named("userAccountDao")
public class UserAccountDaoImpl extends AbstractDao implements UserAccountDao {
	
	public UserAccount getUserAccountById(int userAccountId){
		
		Session session = getSession();
		session.beginTransaction();
		UserAccount userAccount = (UserAccount)session.get(UserAccount.class, userAccountId);
		session.getTransaction().commit();
		session.close();

		return userAccount;
	}
	
}
