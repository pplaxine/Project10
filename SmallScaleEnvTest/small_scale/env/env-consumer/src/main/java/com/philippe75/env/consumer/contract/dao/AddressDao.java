package com.philippe75.env.consumer.contract.dao;

import java.util.List;

import com.philippe75.env.model.Address;

public interface AddressDao {
	
	List<Address> getListAddressForUser(int user_id);
}
