package com.philippe75.libraryWS.business.impl.manager;

import javax.inject.Inject;

import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;

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

	public DaoHandler getDaoHandler() {
		return daoHandler;
	}

}
