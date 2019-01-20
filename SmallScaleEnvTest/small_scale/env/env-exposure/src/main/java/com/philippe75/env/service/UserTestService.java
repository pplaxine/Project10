package com.philippe75.env.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.philippe75.env.business.dto.UserTestDto;

@WebService
@SOAPBinding(style = Style.RPC)
public interface UserTestService {
	
	@WebMethod
	public List<UserTestDto> getListUsers();
}
