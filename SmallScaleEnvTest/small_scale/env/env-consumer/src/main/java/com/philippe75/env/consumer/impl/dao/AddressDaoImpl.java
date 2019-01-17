package com.philippe75.env.consumer.impl.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.philippe75.env.consumer.contract.dao.AddressDao;
import com.philippe75.env.model.Address;

@Named("addressDao")
public class AddressDaoImpl implements AddressDao{
	
	public List<Address> getListAddressForUser(int user_id) {
		
		List<Address> listAdress = new ArrayList<Address>();

		Address address1 = new Address();
		address1.setId(4);
		address1.setStreetNumber("18bis");
		address1.setStreetName("Rue de Paris");
		address1.setCity("Lyon");
		address1.setPostCode(69004);
		listAdress.add(address1);
		
		address1 = new Address();
		address1.setId(5);
		address1.setStreetNumber("24");
		address1.setStreetName("Rue de Blop");
		address1.setCity("Paris");
		address1.setPostCode(75011);
		listAdress.add(address1);
		
		return listAdress;
	}
	
	
}
