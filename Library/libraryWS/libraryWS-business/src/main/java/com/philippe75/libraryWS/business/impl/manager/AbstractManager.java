package com.philippe75.libraryWS.business.impl.manager;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.model.exception.soap.fault.AuthentificationFault;

/**
 * <b>Abstract Class that provides access to Dao layer</b>
 * 
 * @see DaoHandler
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public abstract class AbstractManager {
	
	/**
	 * injection of {@link DaoHandler}
	 */
	@Inject
	DaoHandler daoHandler;
	
	//GETTERS
	protected DaoHandler getDaoHandler() {
		return daoHandler;
	}
	
	//UTILITY METHODS 
	protected AuthentificationFault AuthentificationFaultFactory(String faultCode, String message) {
		AuthentificationFault af = new AuthentificationFault();
		af.setFaultCode(faultCode);
		af.setFaultMessage(message);
		return af;
	}
	


}
