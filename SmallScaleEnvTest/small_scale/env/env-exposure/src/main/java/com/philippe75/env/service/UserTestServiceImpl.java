package com.philippe75.env.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.philippe75.env.business.contract.handler.ManagerHandler;
import com.philippe75.env.business.dto.UserTestDto;
import com.philippe75.env.business.impl.handler.ManagerHandlerImpl;
import com.philippe75.env.consumer.contract.dao.AddressDao;
import com.philippe75.env.consumer.impl.dao.AddressDaoImpl;
import com.philippe75.env.model.Address;



@WebService(endpointInterface="com.philippe75.env.service.UserTestService")
public class UserTestServiceImpl extends SpringBeanAutowiringSupport implements UserTestService{
	

	
	
	//private ManagerHandler managerHandler;
	@Override
	public List<UserTestDto> getListUsers()  {
		
		
		
		ManagerHandler mh = new ManagerHandlerImpl();
		//System.out.println(mh.getUserTestManager().test());


		//List<UserTestDto> myList = mh.getUserTestManager().getAllUser();
		
		//List<UserTestDto> list2 = managerHandler.getUserTestManager().getAllUser();
	
		//--------------------------------------------
		
		
	
		List<UserTestDto> list = new ArrayList<>();
		
		UserTestDto utd = new UserTestDto();
		utd.setId(1);
		utd.setFirstName("Jacques");
		utd.setLastName("Brel");
		utd.setAgeFictif(49);
		utd.setListAddress(new AddressDaoImpl().getListAddressForUser(1));
		
		list.add(utd);
		
		utd = new UserTestDto();
		utd.setId(2);
		utd.setFirstName("Florent");
		utd.setLastName("Pagny");
		utd.setAgeFictif(37);
		list.add(utd);
		//--------------------------------------------
		
		//List<UserTestDto> list2 = mh.getUserTestManager().getAllUser();
		//System.out.println(list2);
		
		return list;
	}

}
