package com.philippe75.front.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.philippe75.front.model.UserTest;

public class GestionUserTestAction extends ActionSupport {
	
	//ATT---------
	
	//entrée
	//private Integer id;
	
	//sortie
	private List<UserTest> listUserTest;
	//private UserTest userTest;
	
	//G&S---------
	public List<UserTest> getListUserTest() {
		return listUserTest;
	}
	
//	public UserTest getUserTest() {
//		return userTest;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	//METHODES--------
	public String doGetAllUser() {				
		UserTest ut; 
		List<UserTest> listut = new ArrayList<UserTest>();
		for (int i = 0; i <4; i++) {
			ut = new UserTest();
			ut.setId(i+1);
			ut.setFirstName("Louis ");
			ut.setLastName("" + (13+i));
			ut.setAge(7*(i+1));
			listut.add(ut);
		}		
		listUserTest = listut;		
		return ActionSupport.SUCCESS;
	}


		
//	public String doGetUserTestById() {
//		if(id == null) {
//			this.addActionError("Vous devenez entrer un identifiant");
//		}else {
//			UserTest ut = new UserTest();
//			ut.setFirstName("Louis");
//			ut.setLastName("Archy");
//			ut.setId(4);
//			ut.setAge(46);
//			userTest = ut;
//		}
//		
//		return (this.hasErrors())?ActionSupport.ERROR:ActionSupport.SUCCESS;
//	}
	
}
