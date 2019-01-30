<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="_include/head.jsp" %>
		<title>Login</title>
	</head>
	<body>
		<%@ include file="_include/header.jsp" %>
		<s:form action="login">
			<s:textfield label="Login" key="userMemberId"/>
			<s:password label="Password" key="password"/>
			<s:submit/>
		</s:form>
	</body>
</html>
