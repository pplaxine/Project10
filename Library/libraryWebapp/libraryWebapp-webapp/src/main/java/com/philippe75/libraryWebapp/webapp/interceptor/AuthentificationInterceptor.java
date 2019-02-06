package com.philippe75.libraryWebapp.webapp.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthentificationInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result;
		if(invocation.getInvocationContext().getSession().get("user") !=null) {
			result = invocation.invoke();
		}else {
			result ="error-forbidden";
		}
		return result;
	}
	
}
