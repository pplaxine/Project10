package com.philippe75.libraryWS.consumer.impl.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.philippe75.libraryWS.consumer.contract.handler.DaoHandler;

/**
 * <b>Abstract Class that provides access to Hibernate sessionFactory</b>
 * 
 * <p>The same SessionFactory will be shared amongst all requests.</p>
 * 
 * @see HibernateConfiguration
 * 
 * @author Philippe plaxine
 * @version 1.0
 */
public abstract class AbstractDao {
	
	/**
	 * injection of {@link SessionFactory}
	 */
	@Inject
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.openSession();
	}
	
}
