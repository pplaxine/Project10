package com.philippe75.libraryWS.business.impl.manager;

import javax.inject.Inject;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;
import com.philippe75.libraryWS.model.exception.soap.fault.LibraryServiceFault;

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
	/**
	 * Creates a LibraryServiceFault.
	 * 
	 * @param faultCode the code of the fault
	 * @param message the message og the fault
	 * @return LibraryServiceFault
	 */
	protected LibraryServiceFault libraryServiceFaultFactory(String faultCode, String message) {
		LibraryServiceFault af = new LibraryServiceFault();
		af.setFaultCode(faultCode);
		af.setFaultMessage(message);
		return af;
	}
	
    /**
     * Renvoie un {@link Validator} de contraintes
     *
     * @return Validator
     */
    protected Validator getConstraintValidator() {
        Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
        ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
        Validator vValidator = vFactory.getValidator();
        return vValidator;
    }


}
