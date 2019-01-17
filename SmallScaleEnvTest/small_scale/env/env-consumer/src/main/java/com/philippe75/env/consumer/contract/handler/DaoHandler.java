package com.philippe75.env.consumer.contract.handler;



import com.philippe75.env.consumer.contract.dao.AddressDao;
import com.philippe75.env.consumer.contract.dao.UserTestDao;

public interface DaoHandler {
	
	UserTestDao getUserTestDao();
	AddressDao getAddressDao();
	
}
