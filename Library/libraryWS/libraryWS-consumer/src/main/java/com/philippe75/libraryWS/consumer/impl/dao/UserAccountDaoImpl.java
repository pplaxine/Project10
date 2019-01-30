package com.philippe75.libraryWS.consumer.impl.dao;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;

import com.philippe75.libraryWS.consumer.contract.dao.UserAccountDao;
import com.philippe75.libraryWS.model.user.UserAccount;

/**
 * <b>Implements UserAccountDao Interface</b>
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
@Named("userAccountDao")
public class UserAccountDaoImpl extends AbstractDao implements UserAccountDao {
	
	/**
	 * Get the {@link UserAccount} with name required.
	 * 
	 * @param userAccountId the id of the user
	 * @return UserAccount the {@link UserAccount} with id required.
	 */
	@Override
	public UserAccount getUserAccountByMemberId(String userMemberId) throws Exception {
		
		UserAccount userAccount =null;
		String sql = "SELECT * FROM user_account WHERE user_member_id = :userMemberId";
		
		Session session = getSession();
		session.beginTransaction();
		try {
			
			userAccount = (UserAccount) session.createSQLQuery(sql).setParameter("userMemberId", userMemberId).addEntity(UserAccount.class).getSingleResult();
			
			session.getTransaction().commit();
			

		}finally {
			if(session != null) {
				session.close();
			}
		}
		return userAccount;
	}
	
	
	
	
	
}
