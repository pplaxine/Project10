package com.philippe75.env.business.impl.manager;

import java.util.Random;

import javax.inject.Named;

import com.philippe75.env.business.contract.manager.UserTestManager;
import com.philippe75.env.business.dto.UserTestDto;
import com.philippe75.env.model.UserTest;

@Named("userTestManager")
public class UserTestManagerImpl extends AbstractManagerImpl implements UserTestManager{

	public UserTestDto getUserById(int user_id) {
		
		UserTest ut = getDaoHandler().getUserTestDao().getUserById(user_id);
		
		UserTestDto utd = new UserTestDto();
		
		utd.setId(ut.getId());
		utd.setFirstName(ut.getFirstName());
		utd.setLastName(ut.getLastName());
		utd.setListAddress(ut.getListAddress());
		utd.setAgeFictif(creerAgeFictifPlusJeune(ut.getAge()));
		
		return utd;
	}
	
	
	private Integer creerAgeFictifPlusJeune(int age) {
		
		int fictifAge = (int)Math.round(Math.random() * ( age));
		
		return fictifAge<18?creerAgeFictifPlusJeune(age):fictifAge;
	}
	
	
}
