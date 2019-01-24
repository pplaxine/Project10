package com.philippe75.front.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;


import generated.usertestservice.UserTestDto;
import generated.usertestservice.UserTestService;
import generated.usertestservice.UserTestServiceImplService;

public class GestionUserTestAction extends ActionSupport {
	
	//ATT---------
	
	//entrée
	//private Integer id;
	
	//sortie
	private List<UserTestDto> listUserTest;
	//private UserTest userTest;
	
	//G&S---------
	public List<UserTestDto> getListUserTest() {
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
		
		List<UserTestDto> listUT = new ArrayList<>(); 

				
		UserTestService uts = new UserTestServiceImplService().getUserTestServiceImplPort();
		
		List<UserTestDto> listUserTestDto = uts.getListUsers().getItem(); 
		
		for (UserTestDto utd : listUserTestDto) {
			
			utd.getId();
			utd.getFirstName();
			utd.getLastName();
			utd.getAgeFictif();
			utd.getListAddress();
			listUT.add(utd);
		}
		
		listUserTest = listUT;		
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
