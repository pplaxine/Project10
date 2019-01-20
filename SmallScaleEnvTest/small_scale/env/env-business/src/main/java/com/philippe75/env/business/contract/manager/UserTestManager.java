package com.philippe75.env.business.contract.manager;

import java.util.List;

import com.philippe75.env.business.dto.UserTestDto;

public interface UserTestManager {
	
	List<UserTestDto> getAllUser();
	
	UserTestDto getUserById(int user_id);
	
	String test();
	
}
