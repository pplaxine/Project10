package com.philippe75.env.consumer.impl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.philippe75.env.consumer.contract.dao.UserTestDao;
import com.philippe75.env.model.UserTest;

@Named("userTest")
public class UserTestDaoImpl extends AbstractDaoImpl implements UserTestDao{

	
	public List<UserTest> getAllUsers() {
		
		UserTest ut; 
		List<UserTest> listut = new ArrayList<UserTest>();
		
		for (int i = 0; i <4; i++) {
			ut = new UserTest();
			ut.setId(i+1);
			ut.setFirstName("Louis ");
			ut.setLastName("" + (14+i));
			ut.setAge(7*(i+1));
			ut.setListAddress(new AddressDaoImpl().getListAddressForUser(1));
			listut.add(ut);
		}					
		return listut; 
	}
	
	
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
