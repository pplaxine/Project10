package com.philippe75.libraryWS.consumer.impl.dao;

import javax.inject.Named;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	 * Retrieve the User with its User member id. 
	 * 
	 * @param userMemberId the member id of the user.
	 * 
	 * @return UserAccount the {@link UserAccount} with the member id required.  
	 */
	@Override
	public UserAccount getUserAccountByMemberId(String userMemberId) throws Exception  {
		
		UserAccount userAccount =null;
		String sql = "SELECT * FROM user_account WHERE user_member_id = :userMemberId";
		
		Session session = getSession();
		session.beginTransaction();
		try {
			
			userAccount = (UserAccount) session.createSQLQuery(sql)	
												.setParameter("userMemberId", userMemberId)
												.addEntity(UserAccount.class)
												.getSingleResult();
			session.getTransaction().commit();
			session.close();
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return userAccount;
	}

	/**
	 * Create a password for users First connection. 
	 * 
	 * @param userMemberId the member id of the user.
	 * @param password user password to save
	 * 
	 * @return UserAccount the {@link UserAccount} if password saved successfully.    
	 */
	@Override
	public UserAccount saveUserAccountPw(String userMemberId, String password) throws Exception {
		UserAccount userAccount =null;
		String sql = " UPDATE user_account SET password = :password WHERE user_member_id = :userMemberId";
		String sql2 = "SELECT * FROM user_account WHERE user_member_id = :userMemberId";
		
		Session session = getSession();
		Transaction tr = session.beginTransaction();
		try {
			
			session.createSQLQuery(sql)
					.setParameter("password", password)
					.setParameter("userMemberId", userMemberId)
					.executeUpdate();
			
			userAccount = (UserAccount) session.createSQLQuery(sql2)
					.setParameter("userMemberId", userMemberId)
					.addEntity(UserAccount.class)
					.getSingleResult();
			session.getTransaction().commit();
			session.close();
		}catch(PersistenceException he) {	
			try {
				tr.rollback();
				System.out.println("Transaction rolled back");
	
			} catch (RuntimeException re) {
				System.out.println("Could not Roll back transaction");
			}
		}finally {
			if(session != null) {
				session.close();
			}
		}
		return userAccount;
	}

	/**
	 * Update users mail reminder status.  
	 * 
	 * @param userMemberId the member id of the user.
	 * @param mailReminder the new state of mail reminder : true mail sent / false stop sending mail. 
	 * 
	 */
	@Override
	public void updateMailReminder(String userMemberId, boolean mailReminder) throws Exception {
		String hql = "UPDATE UserAccount u set u.mailReminder =:mailReminder WHERE u.userMemberId =:userMemberId";
		
		Session session = getSession();
		try {
			session.beginTransaction();
			session.createQuery(hql)
						
						.setParameter("userMemberId", userMemberId)
						.setParameter("mailReminder", mailReminder)
						.executeUpdate();
			session.getTransaction().commit();
			session.close();
			
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}
	
	
	
	
	
	
	
}
