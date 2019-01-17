package com.philippe75.env.consumer.impl.dao;

import javax.inject.Named;

import com.philippe75.env.consumer.contract.dao.UserTestDao;
import com.philippe75.env.model.UserTest;

@Named("userTest")
public class UserTestDaoImpl extends AbstractDaoImpl implements UserTestDao{

	public UserTest getUserById(int user_id) {
			
		UserTest ut = new UserTest();
	
		ut.setId(4);
		ut.setFirstName("Napoléon");
		ut.setLastName("Bonaparte");
		ut.setAge(51);
		ut.setListAddress(getDaoHandler().getAddressDao().getListAddressForUser(2));
	
		return ut;
	}
	
}
