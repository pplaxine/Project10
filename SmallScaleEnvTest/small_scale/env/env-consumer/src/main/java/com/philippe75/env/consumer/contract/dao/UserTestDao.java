package com.philippe75.env.consumer.contract.dao;

import java.util.List;

import com.philippe75.env.model.UserTest;

public interface UserTestDao {
	
	List<UserTest> getAllUsers();
	
	UserTest getUserById(int user_id); 
}
