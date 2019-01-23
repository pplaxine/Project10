package com.philippe75.env.business.impl.manager;

import java.util.ArrayList;
import java.util.List;
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
		utd.setAgeFictif((ut.getAge())+3);
		utd.setListAddress(ut.getListAddress());
		
		return utd;
	}
	
	public List<UserTestDto> getAllUser(){
		
		List<UserTestDto> listUserDto = new ArrayList<>();
		
		List<UserTest> listUser = getDaoHandler().getUserTestDao().getAllUsers();
		UserTestDto utd;
		
		for (UserTest ut : listUser) {
			utd = new UserTestDto();
			utd.setId(ut.getId());
			utd.setFirstName(ut.getFirstName());
			utd.setLastName(ut.getLastName());
			utd.setListAddress(ut.getListAddress());
			utd.setAgeFictif((ut.getAge()+3));
			listUserDto.add(utd);
		}
		
		return listUserDto;
	}

	// ---------------------------- TEST ----------------------------------------------
	@Override
	public String test() {
		
		return "blop";
	}
	
}
