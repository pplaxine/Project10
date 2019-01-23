package com.philippe75.env.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.philippe75.env.business.contract.handler.ManagerHandler;
import com.philippe75.env.business.dto.UserTestDto;
import com.philippe75.env.consumer.impl.dao.AddressDaoImpl;
import com.philippe75.env.model.Address;
import com.philippe75.env.model.Chien;
import com.philippe75.env.model.UserTest;
import com.philippe75.env.model.Vehicule;
import com.sun.mail.util.UUDecoderStream;

public class Main extends HttpServlet {

	public static final String VUE_MAIN ="/WEB-INF/main.jsp";
	
	@Inject
	private ManagerHandler managerHandler;
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		List<UserTestDto> listUserTestDto = managerHandler.getUserTestManager().getAllUser();
//		
//		for (UserTestDto userTestDto : listUserTestDto) {
//			System.out.println(userTestDto.getFirstName() + " " + userTestDto.getLastName() );
//		}
		
		
//		List<UserTestDto> list = managerHandler.getUserTestManager().getAllUser();
//		for (UserTestDto utd : list) {
//			String street;
//			String city;
//			System.out.println(utd.getFirstName() + " " + utd.getLastName() + " à " + utd.getAgeFictif() + " ans et possède les adresses suivante : " );
//			
//			for (Address address : utd.getListAddress()) {
//				street = address.getStreetName();
//				city = address.getCity();
//				System.out.println("Nom de la rue : " + street + " à " + city);
//			}
//		}
//		
		
		//instanciation d'un user
		UserTest ut = new UserTest();
		UserTest ut2 = new UserTest();
		
		//ONE TO ONE (with another Entity) : must be saved first ... 
	//	Vehicule vehicule = new Vehicule();
	//	vehicule.setName("CBF600");
	//	vehicule.setUserTest(ut);

		
	//	Vehicule vehicule1 = new Vehicule();
	//	vehicule1.setName("R1");
	//	vehicule1.setUserTest(ut);		//pour une bydirectional relationship (ManyToOne et OneToMany ) 

		
	//	Vehicule vehicule2 = new Vehicule();
	//	vehicule2.setName("Kawasaki H2R");
	//	vehicule2.setUserTest(ut);

		
	//	Collection<Vehicule> listV = new ArrayList<>();
	//	listV.add(vehicule1);
	//	listV.add(vehicule2);
				
		//MANY TO MANY
		Chien chien1 = new Chien();
		chien1.setName("Millaya");
		chien1.getListUserTest().add(ut);
		chien1.getListUserTest().add(ut2);
		
		Chien chien2 = new Chien();
		chien2.setName("Roofy");
		chien2.getListUserTest().add(ut);
		chien2.getListUserTest().add(ut2);
		
		
//		Collection<Address> listAddress = managerHandler.getUserTestManager().getAllUser().get(0).getListAddress();
//		Collection<Address> listAddressDateAdded = new ArrayList<>();
//		for (Address address : listAddress) {
//			address.setDateCreation(new Date());
//			listAddressDateAdded.add(address);
//		}
		
		
//		Address address = managerHandler.getUserTestManager().getAllUser().get(0).getListAddress().iterator().next();
//		address.setDateCreation((new Date()));
//		
//		Address address2 = managerHandler.getUserTestManager().getAllUser().get(0).getListAddress().iterator().next();
//		address2.setDateCreation((new Date()));
		
		//Att du user 
		ut.setFirstName("Louis ");
		ut.setLastName("Le Grand");
		ut.setAge(48);
//		ut.setHomeAddress(address2);
//		ut.setOfficeAddress(address);
//		ut.setListAddress(listAddressDateAdded);
//		ut.setVehicule(vehicule);
//		ut.setListVehicule(listV);
		ut.getListChien().add(chien1);
		ut.getListChien().add(chien2);
		
		//Att du user 
		ut2.setFirstName("Paul ");
		ut2.setLastName("Le Sage");
		ut2.setAge(48);
		ut2.getListChien().add(chien1);
		ut2.getListChien().add(chien2);
		
		

		
		//SAVE
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(address);
//		session.save(address2);
//		session.getTransaction().commit();
		
		
		//GET
//		session = sessionFactory.openSession();
//		session.beginTransaction();
//		address = (Address)session.get(Address.class, 1);
//		System.out.println(address.getStreetName() + " " + address.getCity() ); 
//		address = (Address)session.get(Address.class, 2);
//		System.out.println(address.getStreetName() + " " + address.getCity() );
		

		
		
		//SAVE WITH EMBEDDED OBJECT 
		Session session = sessionFactory.openSession();
		session.beginTransaction();
	//	session.save(vehicule);
	//	session.save(vehicule1);
	//	session.save(vehicule2);
		session.save(chien1);
		session.save(chien2);
		session.save(ut);
		session.save(ut2);
		session.getTransaction().commit();
		session.close();
		
		//PROXY OBJECT LAZY AND EAGER FETCH (object pulled from database via proxyobject (similar to the object but with simple att only: lazy fetch(by default)) if you want all att of the object to be pulled : Eager fetch(need to specify)
//		session = sessionFactory.openSession();
//		UserTest utfetch = (UserTest)session.get(UserTest.class, 1);
//		//complexe att are fetch only when getter is called (ex : utfetch.getListAddress();) by using session. If session is closed complexe att can't be fetched = error.   
//		session.close();
//		System.out.println(utfetch.getFirstName());		//no error has object has been pulled with simple att
//		System.out.println(utfetch.getListAddress().iterator().next().getStreetName()); 	// error has object has been lazy pulled (without complexe types) //error removed by passing param fetch eager to collection annotation
//		


				
		this.getServletContext().getRequestDispatcher(VUE_MAIN).forward(request, response);
	}

}
